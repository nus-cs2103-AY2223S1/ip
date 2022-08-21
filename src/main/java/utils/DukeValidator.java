package utils;

import java.time.LocalDate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DukeValidator {
    public static LocalDate parseDate(String date) {
        // Regex pattern matcher for a date in yyyy-mm-dd format
        final Pattern matchDate = Pattern.compile("^(?<year>\\d{4})-(?<month>0[0-9]|1[0-2])-(?<day>0[0-9]|1[0-9]|2[0-9]|3[0-1])$");
        Matcher matcher = matchDate.matcher(date);
        if (!matcher.find()) {
            return null;
        }
        return LocalDate.parse(date);
    }
}
