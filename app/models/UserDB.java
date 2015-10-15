package models;

import java.util.ArrayList;
import java.util.List;
import models.formdata.UserFormData;

public class UserDB {

  public static User add(String username) {
    User user = UserDB.get(username);
    if(user == null) {
      user = new User(username);
      user.save();
    }
    return user;
  }

  public static User get(long id) {
    return User.find().where().eq("id", id).findUnique();
  }

  public static User get(String username) {
    return User.find().where().eq("username", username).findUnique();
  }

  public static List<User> getAll() {
    return User.find().all();
  }

  public static List<User> getAllDrivers() {
    return User.find().where().eq("isDriver", true).findList();
  }
  
  public static List<String> getLocations() {
    List<User> users = User.find().where().eq("isDriver", true).where().isNotNull("origin").findList();
    List<String> locations = new ArrayList<>();
    for(User user : users) {
      if(!locations.contains(user.origin())) {
        locations.add(user.origin());
      }
    }
    return locations;
  }

  public static void save(UserFormData formData) {
    User user = UserDB.get(formData.username);
    user.isDriver(formData.isDriver);
    user.name(formData.name);
    user.origin(formData.origin);
    user.comment(formData.comment);
    user.noSmoking(formData.noSmoking);
    user.noEating(formData.noEating);
    user.noDrinking(formData.noDrinking);
    
    user.arrivalM(formData.arrivalM);
    user.arrivalT(formData.arrivalT);
    user.arrivalW(formData.arrivalW);
    user.arrivalR(formData.arrivalR);
    user.arrivalF(formData.arrivalF);
    user.returnM(formData.returnM);
    user.returnT(formData.returnT);
    user.returnW(formData.returnW);
    user.returnR(formData.returnR);
    user.returnF(formData.returnF);

    user.hideAlerts(formData.hideAlerts);
    user.emailNotifications(formData.emailNotifications);
    user.textNotifications(formData.textNotifications);
    user.phone(formData.phone);
    user.carrier(formData.carrier);
    user.save();
  }

}