package controllers;

import play.mvc.Http.Context;
import play.mvc.Result;
import play.mvc.Security;
import models.User;
import models.UserDB;

/**
 * Authenticator for the application.
 * See the <code>http://www.playframework.com/documentation/2.2.x/api/java/play/mvc/Security.Authenticator.html</code> for more info.
 */
public class Secured extends Security.Authenticator {

  @Override
  public String getUsername(Context ctx) {
    String username = ctx.session().get("username");
    // check if the user is in the in memory database
    User user = UserDB.get(username);
    if(user != null) {
      return username;
    }
    return null;
  }

  @Override
  public Result onUnauthorized(Context ctx) {
    return redirect(routes.Application.home());
  }
}