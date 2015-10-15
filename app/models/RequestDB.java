package models;

import java.util.List;
import models.formdata.RequestFormData;

public class RequestDB {

  public static Request add(RequestFormData formData) {
    Request request = new Request(UserDB.get(formData.driverUsername), UserDB.get(formData.requesterUsername));
    request.arrivalM(formData.arrivalM);
    request.arrivalT(formData.arrivalT);
    request.arrivalW(formData.arrivalW);
    request.arrivalR(formData.arrivalR);
    request.arrivalF(formData.arrivalF);
    request.returnM(formData.returnM);
    request.returnT(formData.returnT);
    request.returnW(formData.returnW);
    request.returnR(formData.returnR);
    request.returnF(formData.returnF);
    request.message(formData.message);
    request.save();
    return request;
  }
  
  public static Request get(long id) {
    return Request.find().where().eq("id", id).findUnique();
  }

  public static List<Request> getAllByRequester(User requester) {
    return Request.find().where().eq("requester", requester).findList();
  }
  
  public static List<Request> getAllByDriver(User driver) {
    return Request.find().where().eq("driver", driver).findList();
  }

}