package models;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import play.db.ebean.Model;

@Entity
public class User extends Model {
  
  private static final long serialVersionUID = 8940966676421811468L;

  /**
   * The EBean ORM finder method for database queries.
   * @return The finder method for User.
   */
  public static Finder<Long, User> find() {
    return new Finder<Long, User>(Long.class, User.class);
  }

  /*
   * User instance scope.
   **************************************************************************/
  @Id
  private long id;

  @OneToMany(mappedBy = "requester")
  private List<Request> requestsAsRequester = new ArrayList<>();
  @OneToMany(mappedBy = "driver")
  private List<Request> requestsAsDriver = new ArrayList<>();
  
  /** UH username */
  private String username;
  private String name;
  private boolean isDriver;
  private String origin;
  private String comment;
  private String phone;
  private String carrier;
  // images
  @Lob
  private byte[] userImage;
  @Lob
  private byte[] vehicleImage;
  // preferences
  private boolean noSmoking;
  private boolean noEating;
  private boolean noDrinking;
  public boolean hideAlerts;
  public boolean emailNotifications;
  public boolean textNotifications;
  // times
  private String arrivalM;
  private String arrivalT;
  private String arrivalW;
  private String arrivalR;
  private String arrivalF;
  private String returnM;
  private String returnT;
  private String returnW;
  private String returnR;
  private String returnF;

  /**
   * Constructor. Used internally. Should <strong>NOT</strong> be called directly.
   */
  public User(String username){
    this.username = username;
    this.name = this.origin = this.comment = "";
    this.isDriver = false;
    this.phone = this.carrier = "";
    this.noSmoking = this.noDrinking = this.noEating = false;
    this.emailNotifications = this.textNotifications = false;

    this.arrivalM = this.arrivalT = this.arrivalW = this.arrivalR = this.arrivalF = "";
    this.returnM = this.returnT = this.returnW = this.returnR = this.returnF = "";
  }

  /** Getter for id. */
  public long id() { return this.id; }

  /** Getter for username. */
  public String username() { return this.username; }
  /** Setter for username. */
  public String username(String value) { return (this.username = value); }

  /** Getter for name. */
  public String name() { return this.name; }
  /** Setter for name. */
  public String name(String value) { return (this.name = value); }

  /** Getter for isDriver. */
  public boolean isDriver() { return this.isDriver; }
  /** Setter for isDriver. */
  public boolean isDriver(boolean value) { return (this.isDriver = value); }

  /** Getter for origin. */
  public String origin() { return this.origin; }
  /** Setter for origin. */
  public String origin(String value) { return (this.origin = value); }

  /** Getter for comment. */
  public String comment() { return this.comment; }
  /** Setter for comment. */
  public String comment(String value) { return (this.comment = value); }
  
  /** Getter for phone. */
  public String phone() { return this.phone; }
  /** Setter for phone. */
  public String phone(String value) { return (this.phone = value); }
  
  /** Getter for carrier. */
  public String carrier() { return this.carrier; }
  /** Setter for carrier. */
  public String carrier(String value) { return (this.carrier = value); }

  /** Getter for userImage. */
  public byte[] userImage() {
    if(this.userImage == null || this.userImage.length == 0) {
      return null;
    }
    return this.userImage;
  }
  /** Setter for userImage. */
  public byte[] userImage(byte[] value) { return (this.userImage = value); }

  /** Getter for vehicleImage. */
  public byte[] vehicleImage() {
    if(this.vehicleImage == null || this.vehicleImage.length == 0) {
      return null;
    }
    return this.vehicleImage;
  }
  /** Setter for vehicleImage. */
  public byte[] vehicleImage(byte[] value) { return (this.vehicleImage = value); }

  /** Getter for noSmoking. */
  public boolean noSmoking() { return this.noSmoking; }
  /** Setter for noSmoking. */
  public boolean noSmoking(boolean value) { return (this.noSmoking = value); }

  /** Getter for noEating. */
  public boolean noEating() { return this.noEating; }
  /** Setter for noEating. */
  public boolean noEating(boolean value) { return (this.noEating = value); }

  /** Getter for noDrinking. */
  public boolean noDrinking() { return this.noDrinking; }
  /** Setter for noDrinking. */
  public boolean noDrinking(boolean value) { return (this.noDrinking = value); }

  /** Getter for hideAlerts. */
  public boolean hideAlerts() { return hideAlerts; }
  /** Setter for hideAlerts. */
  public boolean hideAlerts(boolean value) { return (this.hideAlerts = value); }
  
  /** Getter for emailNotifications. */
  public boolean emailNotifications() { return emailNotifications; }
  /** Setter for hideAlerts. */
  public boolean emailNotifications(boolean value) { return (this.emailNotifications = value); }
  
  /** Getter for textNotifications. */
  public boolean textNotifications() { return textNotifications; }
  /** Setter for textNotifications. */
  public boolean textNotifications(boolean value) { return (this.textNotifications = value); }

  /** Getter for arrivalM. */
  public String arrivalM() { return this.arrivalM; }
  /** Setter for arrivalM. */
  public String arrivalM(String value) { return (this.arrivalM = value); }
  
  /** Getter for arrivalT. */
  public String arrivalT() { return this.arrivalT; }
  /** Setter for arrivalT. */
  public String arrivalT(String value) { return (this.arrivalT = value); }
  
  /** Getter for arrivalW. */
  public String arrivalW() { return this.arrivalW; }
  /** Setter for arrivalW. */
  public String arrivalW(String value) { return (this.arrivalW = value); }
  
  /** Getter for arrivalR. */
  public String arrivalR() { return this.arrivalR; }
  /** Setter for arrivalR. */
  public String arrivalR(String value) { return (this.arrivalR = value); }
  
  /** Getter for arrivalF. */
  public String arrivalF() { return this.arrivalF; }
  /** Setter for arrivalF. */
  public String arrivalF(String value) { return (this.arrivalF = value); }

  /** Getter for returnM. */
  public String returnM() { return this.returnM; }
  /** Setter for returnM. */
  public String returnM(String value) { return (this.returnM = value); }
  
  /** Getter for returnT. */
  public String returnT() { return this.returnT; }
  /** Setter for returnT. */
  public String returnT(String value) { return (this.returnT = value); }
  
  /** Getter for returnW. */
  public String returnW() { return this.returnW; }
  /** Setter for returnW. */
  public String returnW(String value) { return (this.returnW = value); }
  
  /** Getter for returnR. */
  public String returnR() { return this.returnR; }
  /** Setter for returnR. */
  public String returnR(String value) { return (this.returnR = value); }
  
  /** Getter for returnF. */
  public String returnF() { return this.returnF; }
  /** Setter for returnF. */
  public String returnF(String value) { return (this.returnF = value); }
}
