package controllers;

import models.formdata.Carriers;
import com.typesafe.plugin.*;


public class Email {
  /**
   * Sends Accepted Email.
   */
  public static void sendMessageAccepted(String eAddress,String comment) {
    MailerAPI mail = play.Play.application().plugin(MailerPlugin.class).email();
    String url = (eAddress + "@hawaii.edu");
    mail.setSubject("Carpool Request:");
    mail.setRecipient(url);
    mail.setFrom("carpooluh@gmail.com");
    mail.send("Hello in regards to your request you have been accepted" + comment );
  }
  /**
   * Sends accepted text.
   */
  public static void sendTextAccepted(String phone, String carrier,String eAddress,String comment) {
    String concatonation = models.formdata.Carriers.getCarrier(carrier);
    String smallPhone = phone.replaceAll("\\D+", "");
    String full = smallPhone + concatonation;
    MailerAPI mail = play.Play.application().plugin(MailerPlugin.class).email();
    mail.setSubject("Carpool Request:");
    mail.setRecipient(full);
    mail.setFrom("carpooluh@gmail.com");
    mail.send("Hello in regards to your request you have been accepted" + comment );
  }
  /**
   * Sends Rejected Email.
   */
  public static void sendMessageRejected(String eAddress,String comment) {
    MailerAPI mail = play.Play.application().plugin(MailerPlugin.class).email();
    String url = (eAddress + "@hawaii.edu");
    mail.setSubject("Carpool Request:");
    mail.setRecipient(url);
    mail.setFrom("carpooluh@gmail.com");
    mail.send("Hello in regards to your request you have been rejected" + comment );
  }
  /**
   * Sends Rejected text.
   */
  public static void sendTextRejected(String phone, String carrier,String eAddress,String comment) {
    String concatonation = models.formdata.Carriers.getCarrier(carrier);
    String smallPhone = phone.replaceAll("\\D+", "");
    String full = smallPhone + concatonation;
    MailerAPI mail = play.Play.application().plugin(MailerPlugin.class).email();
    mail.setSubject("Carpool Request:");
    mail.setRecipient(full);
    mail.setFrom("carpooluh@gmail.com");
    mail.send("Hello in regards to your request you have been rejected" + comment );
  }
}
