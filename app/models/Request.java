package models;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import play.db.ebean.Model;

@Entity
public class Request extends Model {

  private static final long serialVersionUID = 6306199747839480932L;
  
  /**
   * The EBean ORM finder method for database queries.
   * @return The finder method for Request.
   */
  public static Finder<Long, Request> find() {
    return new Finder<Long, Request>(Long.class, Request.class);
  }

  @Id
  private long id;

  @ManyToOne
  private User requester;
  
  @ManyToOne
  private User driver;

  private boolean arrivalM;
  private boolean arrivalT;
  private boolean arrivalW;
  private boolean arrivalR;
  private boolean arrivalF;

  private boolean returnM;
  private boolean returnT;
  private boolean returnW;
  private boolean returnR;
  private boolean returnF;

  private String message;
  private String responseMessage;
  private long timestamp;

  private String status;

  public Request(User driver, User requester) {
    
    this.driver = driver;
    this.requester = requester;

    this.arrivalM = this.arrivalT = this.arrivalW = this.arrivalR = this.arrivalF = false;
    this.returnM = this.returnT = this.returnW = this.returnR = this.returnF = false;
    this.message = this.responseMessage = "";
    this.timestamp = (new Date()).getTime();

    this.status = RequestStatus.PENDING;
  }

  public long id() {
    return this.id;
  }

  public User requester() {
    return this.requester;
  }

  public User driver() {
    return this.driver;
  }

  public boolean arrivalM(boolean value) {
    return (this.arrivalM = value);
  }
  public boolean arrivalT(boolean value) {
    return (this.arrivalT = value);
  }
  public boolean arrivalW(boolean value) {
    return (this.arrivalW = value);
  }
  public boolean arrivalR(boolean value) {
    return (this.arrivalR = value);
  }
  public boolean arrivalF(boolean value) {
    return (this.arrivalF = value);
  }

  public boolean returnM(boolean value) {
    return (this.returnM = value);
  }
  public boolean returnT(boolean value) {
    return (this.returnT = value);
  }
  public boolean returnW(boolean value) {
    return (this.returnW = value);
  }
  public boolean returnR(boolean value) {
    return (this.returnR = value);
  }
  public boolean returnF(boolean value) {
    return (this.returnF = value);
  }

  public String message() {
    return this.message;
  }
  public String message(String value) {
    return (this.message = value);
  }

  public String responseMessage() {
    return this.responseMessage;
  }
  public String responseMessage(String value) {
    return (this.responseMessage = value);
  }
  
  public long timestamp() {
    return this.timestamp;
  }

  public String status() {
    return this.status;
  }
  public String status(String value) {
    return (this.status = value);
  }

  public String timeM() {
    String result = "";
    if(this.arrivalM || this.returnM) {
      result += "M:";
      if(this.arrivalM) {
        result += " " + this.driver().arrivalM();
      }
      if(this.returnM) {
        result += " " + this.driver().returnM();
      }
    }
    return result;
  }
  public String timeT() {
    String result = "";
    if(this.arrivalT || this.returnT) {
      result += "T:";
      if(this.arrivalT) {
        result += " " + this.driver().arrivalT();
      }
      if(this.returnT) {
        result += " " + this.driver().returnT();
      }
    }
    return result;
  }
  public String timeW() {
    String result = "";
    if(this.arrivalW || this.returnW) {
      result += "W:";
      if(this.arrivalW) {
        result += " " + this.driver().arrivalW();
      }
      if(this.returnW) {
        result += " " + this.driver().returnW();
      }
    }
    return result;
  }
  public String timeR() {
    String result = "";
    if(this.arrivalR || this.returnR) {
      result += "R:";
      if(this.arrivalR) {
        result += " " + this.driver().arrivalR();
      }
      if(this.returnR) {
        result += " " + this.driver().returnR();
      }
    }
    return result;
  }
  public String timeF() {
    String result = "";
    if(this.arrivalF || this.returnF) {
      result += "F:";
      if(this.arrivalF) {
        result += " " + this.driver().arrivalF();
      }
      if(this.returnF) {
        result += " " + this.driver().returnF();
      }
    }
    return result;
  }
}
