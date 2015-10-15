package controllers;

import java.io.File;
import java.io.IOException;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Date;
import java.util.Map;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import models.Request;
import models.RequestDB;
import models.User;
import models.UserDB;
import models.RequestStatus;
import models.formdata.Carriers;
import models.formdata.AcceptRejectRequestFormData;
import models.formdata.RequestFormData;
import models.formdata.UserFormData;
import org.apache.commons.codec.binary.Base64;
import org.w3c.dom.Document;
import com.typesafe.plugin.*;
import play.Play;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Http.MultipartFormData;
import play.mvc.Http.MultipartFormData.FilePart;
import play.mvc.Result;
import play.mvc.Security;
import views.html.AppInterface;
import views.html.AppProfile;
import views.html.AppRequests;
import views.html.Home;

public class Application extends Controller {
  
  private static final String CAS_LOGIN = "https://cas-test.its.hawaii.edu/cas/login";
  private static final String CAS_VALIDATE = "https://cas-test.its.hawaii.edu/cas/serviceValidate";
  private static final String CAS_LOGOUT = "https://cas-test.its.hawaii.edu/cas/logout";
  
  /**
   * This is the home page.
   * This page basically a static page with information and a login button.
   */
  public static Result home() {
    Data data = new Data();
    data.set("pageTitle", "Carpools UH");
    return ok(Home.render(data));
  }
  
  /**
   * This is the Login page.
   * Redirects to CAS login, verifies the user, and redirects to the app interface
   */
  public static Result login() throws Exception {
    Map<String, String[]> query = request().queryString();
    String serviceURL = routes.Application.login().absoluteURL(request());
    serviceURL = URLEncoder.encode(serviceURL, "UTF-8");
    if(query.size() == 0) {
      return redirect(CAS_LOGIN + "?service=" + serviceURL);
    } else {
      String[] tickets = query.get("ticket");
      if(tickets.length > 0) {
        String ticket = tickets[0];
        String validateURL = CAS_VALIDATE + "?service=" + serviceURL + "&ticket=" + ticket;
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = dbf.newDocumentBuilder();
        Document doc = db.parse(new URL(validateURL).openStream());
        doc.getElementsByTagName("cas:serviceResponse");
        boolean success = doc.getElementsByTagName("cas:authenticationSuccess").getLength() > 0;
        if(success) {
          String username = doc.getElementsByTagName("cas:user").item(0).getTextContent();
          session().clear();
          session("username", username);
          UserDB.add(username);
          return redirect(routes.Application.appInterface());
        }
      }
      return redirect(routes.Application.home());
    }
  }

  /**
   * This is the Logout page.
   * Clears the session and redirects to the home page.
   */
  public static Result logout() throws Exception {
    session().clear();
    String serviceURL = routes.Application.home().absoluteURL(request());
    serviceURL = URLEncoder.encode(serviceURL, "UTF-8");
    return redirect(CAS_LOGOUT + "?service=" + serviceURL);
  }
  
  /**
   * This is the main interface page.
   */
  @Security.Authenticated(Secured.class)
  public static Result appInterface() {
    Data data = new Data();
    data.set("pageTitle", "Carpools UH");
    data.set("user", UserDB.get(session().get("username")));
    data.set("locations", UserDB.getLocations());
    data.set("drivers", UserDB.getAllDrivers());
    return ok(AppInterface.render(data, null));
  }

  /**
   * This is the profile page.
   */
  @Security.Authenticated(Secured.class)
  public static Result appProfile() {
    User user = UserDB.get(session().get("username"));
    Data data = new Data();
    data.set("pageTitle", "Carpools UH");
    data.set("user", user);
    Form<UserFormData> userForm = Form.form(UserFormData.class).fill(new UserFormData(user));
    return ok(AppProfile.render(data, userForm));
  }

  /**
   * Save the profile information.
   * @throws IOException 
   */
  @Security.Authenticated(Secured.class)
  public static Result saveProfile() throws IOException {
    User user = UserDB.get(session().get("username"));
    Data data = new Data();
    data.set("pageTitle", "Carpools UH");
    data.set("user", user);
    
    Form<UserFormData> userForm = Form.form(UserFormData.class).bindFromRequest();
    if(userForm.hasErrors()) {
      return badRequest(AppProfile.render(data, userForm));
    }

    UserFormData userFormData = userForm.get();
    UserDB.save(userFormData);
    
    MultipartFormData body = request().body().asMultipartFormData();
    FilePart userImage = body.getFile("userImage");
    if(userImage != null) {
      File source = userImage.getFile();
      user.userImage(Application.toByteArray(source));
      System.out.println(user.userImage().length);
    }
    FilePart vehicleImage = body.getFile("vehicleImage");
    if(vehicleImage != null) {
      File source = vehicleImage.getFile();
      user.vehicleImage(Application.toByteArray(source));
      System.out.println(user.vehicleImage().length);
    }
    user.save();

    flash().put("success", "Changes saved.");
    return redirect(routes.Application.appProfile());
  }

  /**
   * This is the requests page.
   */
  @Security.Authenticated(Secured.class)
  public static Result appRequests() {
    User user = UserDB.get(session().get("username"));
    Data data = new Data();
    data.set("pageTitle", "Carpools UH");
    data.set("user", user);
    data.set("requestsAsRequester", RequestDB.getAllByRequester(user));
    data.set("requestsAsDriver", RequestDB.getAllByDriver(user));
    return ok(AppRequests.render(data));
  }

  /**
   * Creates a new request.
   */
  @Security.Authenticated(Secured.class)
  public static Result sendRequest() {

    Form<RequestFormData> requestForm = Form.form(RequestFormData.class).bindFromRequest();
    if(requestForm.hasErrors()) {
      User user = UserDB.get(session().get("username"));
      Data data = new Data();
      data.set("pageTitle", "Carpools UH");
      data.set("user", user);
      data.set("locations", UserDB.getLocations());
      data.set("drivers", UserDB.getAllDrivers());
      return badRequest(AppInterface.render(data, requestForm));
    } else {
      RequestFormData requestFormData = requestForm.get();
      RequestDB.add(requestFormData);
      flash("success", "Request successfully sent to " + UserDB.get(requestFormData.driverUsername).name());
    }
    return redirect(routes.Application.appInterface());
  }

  /**
   * Creates a new request.
   */
  @Security.Authenticated(Secured.class)
  public static Result acceptRejectRequest() {

    Form<AcceptRejectRequestFormData> requestForm = Form.form(AcceptRejectRequestFormData.class).bindFromRequest();
    // no need to check for errors

    AcceptRejectRequestFormData requestFormData = requestForm.get();
    Request request = RequestDB.get(requestFormData.requestId);

    switch(requestFormData.type) {
      case "accept":
        request.status(RequestStatus.ACCEPTED);
        break;
      case "reject":
        request.status(RequestStatus.REJECTED);
        break;
    }
    request.responseMessage(requestFormData.message);
    request.save();

    // Brent, here's where you can put the method to send email/sms
    // using request.requester().username();
   String requester =  request.requester().username();
   String message = request.message();
   if(request.requester().emailNotifications() == true && request.status().equals("Accepted")) {
     String url = (requester + "@hawaii.edu");
     Email.sendMessageAccepted(requester, message);
   }
   if(request.requester().textNotifications() == true && request.status().equals("Accepted")) {
     String phone = request.requester().phone();
     String carrier = request.requester().carrier();
     Email.sendTextAccepted(phone, carrier, requester, message);
   }
   
   if(request.requester().emailNotifications() == true && request.status().equals("Rejected")) {
     String url = (requester + "@hawaii.edu");
     Email.sendMessageRejected(requester, message);
   }
   
   if(request.requester().textNotifications() == true && request.status().equals("Rejected")) {
     String phone = request.requester().phone();
     String carrier = request.requester().carrier();
     Email.sendTextRejected(phone, carrier, requester, message);
   }
    return redirect(routes.Application.appRequests());
  }

  /**
   * Gets the userImage
   */
  public static Result userImage(long userId) {
    byte[] image = UserDB.get(userId).userImage();
    if(image != null) {
      return ok(image).as("image/jpeg");
    }
    return redirect(routes.Assets.at("images/default.png"));
  }

  public static Result vehicleImage(long userId) {
    byte[] image = UserDB.get(userId).vehicleImage();
    if(image != null) {
      return ok(image).as("image/jpeg");
    }
    return redirect(routes.Assets.at("images/default.png"));
  }
  
  /**
   * Converts the File into a byte[]
   */
  public static byte[] toByteArray(File file) {
    try {
      Path path = file.toPath();
      byte[] bytes = Files.readAllBytes(path);
      return bytes;
    } catch (IOException e) {
      e.printStackTrace();
      return null;
    }
  }
  
  /**
  * Sends a message to admin.
  *
  * @return the page if any errors, index otherwise
  */
  public static Result sendMessage(String eAddress,String comment) {
    System.out.println("I GOT HERE");
    MailerAPI mail = play.Play.application().plugin(MailerPlugin.class).email();
    String url = (eAddress + "@hawaii.edu");
    System.out.println(url);
    mail.setSubject("Carpool Request:");
    mail.setRecipient(url);
    mail.setFrom("carpooluh@gmail.com");
    mail.send("Hello in regards to your request you have been accepted" + comment );
    return redirect(routes.Application.appRequests());
  }
  
  public static Result sendText(String phone, String carrier,String eAddress,String comment) {
    String concatonation = Carriers.getCarrier(carrier);
    MailerAPI mail = play.Play.application().plugin(MailerPlugin.class).email();
    mail.setSubject("Carpool Request:");
    mail.setRecipient("Carpool Email", phone + concatonation);
    mail.setFrom("carpooluh@gmail.com");
    mail.send("Hello in regards to your request you have been accepted" + comment );
    return redirect(routes.Application.appRequests());
  }
    
}
