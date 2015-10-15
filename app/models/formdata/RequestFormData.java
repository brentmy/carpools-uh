package models.formdata;

import java.util.ArrayList;
import java.util.List;
import play.data.validation.ValidationError;

public class RequestFormData {

  public String requesterUsername;
  public String driverUsername;

  public boolean timeValidation;
  public boolean arrivalM;
  public boolean arrivalT;
  public boolean arrivalW;
  public boolean arrivalR;
  public boolean arrivalF;
  public boolean returnM;
  public boolean returnT;
  public boolean returnW;
  public boolean returnR;
  public boolean returnF;

  public String message;
  
  public boolean riderIsAccepted;
  public boolean riderIsRejected;
  public boolean driverAccepted;
  public boolean driverRejected;

  /**
   * Constructor. Used internally. Should <strong>NOT</strong> be called directly.
   */
  public RequestFormData() {
    this.requesterUsername = "";
    this.driverUsername = "";

    this.timeValidation = false;
    this.arrivalM = this.arrivalT = this.arrivalW = this.arrivalR = this.arrivalF = false;
    this.returnM = this.returnT = this.returnW = this.returnR = this.returnF = false;

    this.message = "";
    
    this.riderIsAccepted = false;
    this.riderIsRejected = false;
    this.driverAccepted = false;
    this.driverRejected = false;
  }

  public List<ValidationError> validate() {
    List<ValidationError> errors = new ArrayList<>();
    
    if(!arrivalM && !arrivalT && !arrivalW && !arrivalR && !arrivalF &&
       !returnM && !returnT && !returnW && !returnR && !returnF) {
      errors.add(new ValidationError("timeValidation", "At least one day/time must be chosen."));
    }

    return errors.isEmpty() ? null : errors;
  }
}
