package duke;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

/**
 * Parses Date from String
 */
public class DateParser {
    /**
     *
     * @param date
     * @return LocalDate object for the date string
     */
    public static LocalDate parseDate(String date){
        LocalDate parsedDate = null;
        try{
            parsedDate = LocalDate.parse(date);
        }catch (DateTimeParseException e) {
            e.printStackTrace();
        }
        return parsedDate;
    }
}
