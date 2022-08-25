package duke;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class DateParser {
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
