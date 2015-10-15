package models.formdata;

import java.util.HashMap;
import java.util.Map;

/**
 * Represents surfer types.
 */

public class Carriers {
  
  private static String[] types = {"T-Mobile", "Verizon", "AT&T", "Boost", "Sprint", "Alltell", "Virgin", "US Cellular"};
  
  /**
   * Returns an initialized Map of carrier types (when no type is selected).
   * @return The carrier type map.
   */
  public static Map<String, Boolean> getTypes() {
    Map<String, Boolean> typeMap = new HashMap<>();
    for (String type : types) {
      typeMap.put(type, false);
    }
    return typeMap;
  }

  /**
   * Returns an initialized Map of carrier types with a selected value.
   * @return The carrier type map.
   */
  public static Map<String, Boolean> getTypes(String selected) {
    Map<String, Boolean> typeMap = new HashMap<>();
    for (String type : types) {
      typeMap.put(type, type.equals(selected));
    }
    return typeMap;
  }

  public static String getCarrier(String carrier) {
    if (carrier.equals("T-Mobile")) {
      return "@tmomail.net";
    }
    else if (carrier.equals("Verizon")) {
      return "@vtext.com";
    }
    else if (carrier.equals("AT&T")) {
      return "@txt.att.net";
    }
    else if (carrier.equals("Boost")) {
      return "@myboostmobile.com";
    }
    else if (carrier.equals("Sprint")) {
      return "@messaging.sprintpcs.com";
    }
    else if (carrier.equals("US Cellular")) {
      return "@email.uscc.net";
    }
    else if (carrier.equals("Alltel")) {
      return "@message.alltel.com";
    }
    else if(carrier.equals("Virgin")) {
      return "@vmobl.com";
    }
    return "";
  }
  
  /**
   * Returns true if carrier is valid type.
   * @param carriers The carrier type.
   * @return True if valid, else false.
   */
  public static boolean isType(String carriers) {
    return Carriers.getTypes().keySet().contains(carriers);
  }
}
