package models.formdata;

public class AcceptRejectRequestFormData {

  public long requestId;
  public String message;
  public String type;

  /**
   * Constructor. Used internally. Should <strong>NOT</strong> be called directly.
   */
  public AcceptRejectRequestFormData() {
    this.requestId = -1;
    this.message = "";
    this.type = "";
  }
}
