package models.formdata;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
 
public class Time12HoursValidator{
 
    private Pattern pattern;
    private Matcher matcher;
 
    private static final String TIME12HOURS_PATTERN = 
                                "(1[012]|[1-9]):[0-5][0-9](\\s)?(?i)(am|pm)";
 
    public Time12HoursValidator(){
      pattern = Pattern.compile(TIME12HOURS_PATTERN);
    }
 
    /**
     * Validate time in 12 hours format with regular expression
     * @param time time address for validation
     * @return true valid time format, false invalid time format
     */
    public boolean validate(String time){     
      matcher = pattern.matcher(time);
      return matcher.matches();           
    }
}

