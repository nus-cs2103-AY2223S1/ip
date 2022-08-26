package duke.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public abstract class DateAndTimeParser {

    public static String convertDate(LocalDate dateTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd uuuu");
        return dateTime.format(formatter);
    }

    public static LocalDate validateAndParse(String dateTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate formattedDateTime = null;
        try {
            formattedDateTime = LocalDate.parse(dateTime, formatter);
        } catch (DateTimeParseException e) {
            return null;
        }
        return formattedDateTime;
    }
}
