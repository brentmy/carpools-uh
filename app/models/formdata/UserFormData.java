package models.formdata;

import java.util.ArrayList;
import java.util.List;
import play.data.validation.ValidationError;
import models.User;

public class UserFormData {
 
  public String username;
  public String name;
  public boolean isDriver;
  public String origin;
  public String comment;
  public String phone;
  public String carrier;

  public boolean noSmoking;
  public boolean noEating;
  public boolean noDrinking;
  public boolean sameGender;
  public boolean hideAlerts;
  public boolean emailNotifications;
  public boolean textNotifications;

  public boolean timeValidation;
  public String arrivalM;
  public String arrivalT;
  public String arrivalW;
  public String arrivalR;
  public String arrivalF;
  public String returnM;
  public String returnT;
  public String returnW;
  public String returnR;
  public String returnF;

 
  
  /**
   * Constructor. Used internally. Should <strong>NOT</strong> be called directly.
   */
  public UserFormData() {
    this.username = "";
    this.name = "";
    this.isDriver = false;
    this.origin = "";
    this.comment = "";
    this.phone = "";
    this.carrier = "";

    this.noSmoking = false;
    this.noEating = false;
    this.noDrinking = false;
    this.sameGender = false;
    this.hideAlerts = false;
    this.emailNotifications = false;
    this.textNotifications = false;

    this.timeValidation = false;
    this.arrivalM = this.arrivalT = this.arrivalW = this.arrivalR = this.arrivalF = "";
    this.returnM = this.returnT = this.returnW = this.returnR = this.returnF = "";
  }

  /**
   * Constructor. Fills the form data with the data from the <code>User</code> given.
   */
  public UserFormData(User user) {
    this.username = user.username();
    this.name = user.name();
    this.isDriver = user.isDriver();
    this.origin = user.origin();
    this.comment = user.comment();
    this.phone = user.phone();
    this.carrier = user.carrier();

    this.noSmoking = user.noSmoking();
    this.noEating = user.noEating();
    this.noDrinking = user.noDrinking();
    this.hideAlerts = user.hideAlerts();
    this.emailNotifications = user.emailNotifications();
    this.textNotifications = user.textNotifications();

    this.timeValidation = false;
    this.arrivalM = user.arrivalM();
    this.arrivalT = user.arrivalT();
    this.arrivalW = user.arrivalW();
    this.arrivalR = user.arrivalR();
    this.arrivalF = user.arrivalF();
    this.returnM = user.returnM();
    this.returnT = user.returnT();
    this.returnW = user.returnW();
    this.returnR = user.returnR();
    this.returnF = user.returnF();
  }
  
  public List<ValidationError> validate() {
    List<ValidationError> errors = new ArrayList<>();
    
    if(this.isDriver) {
      if(this.name.isEmpty()) {
        errors.add(new ValidationError("name", "Name is required if you want to be a driver."));        
      }
      if(this.origin.isEmpty()) {
        errors.add(new ValidationError("origin", "Origin is required if you want to be a driver."));
      }
      //if(this.userImage.isEmpty()) {errors.add(new ValidationError("userImage", "User Picture is required if you want to be a driver."));}
      //if(this.vehicleImage.isEmpty()) {errors.add(new ValidationError("vehicleImage", "Vehicle image is required if you want to be a driver."));}
      if(this.arrivalM.isEmpty() && this.arrivalT.isEmpty() && this.arrivalW.isEmpty() && this.arrivalR.isEmpty() && this.arrivalF.isEmpty() 
          && this.returnM.isEmpty() && this.returnT.isEmpty() && this.returnW.isEmpty() && this.returnR.isEmpty() && this.returnF.isEmpty()) {
        errors.add(new ValidationError("timeValidation", "At least one time must be entered if you want to be a driver"));
      }
    }
    
    if(this.textNotifications) {
      if(this.phone.isEmpty()) {
        errors.add(new ValidationError("phone", "Phone number is required to receive text notifications."));
      }
    }
    
    if(!this.phone.isEmpty()) {
      String phone = this.phone.replaceAll("\\D+", "");
      System.out.println(phone);
      System.out.println(phone.length());
      if(phone.length() != 10) {
        errors.add(new ValidationError("phone", "Phone number must be 10 digits."));
      } else {
        this.phone = "(" + phone.substring(0, 3) + ") " + phone.substring(3, 6) + "-" + phone.substring(6, 10);
      }
    }

    return errors.isEmpty() ? null : errors;
  }
}
