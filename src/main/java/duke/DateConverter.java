package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class DateConverter {
    private static final DateTimeFormatter dateFormatter = DateTimeFormatter.ISO_LOCAL_DATE;

    public static LocalDate convertToLocalDate(String dateString) {
        return LocalDate.parse(dateString, dateFormatter);
    }

    public static String convertToString(LocalDate date) {
        return date.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
    }

    public static boolean isValidDate(String dateString) {
        try {
            LocalDate.parse(dateString, dateFormatter);
        } catch (DateTimeParseException e) {
            return false;
        }
        return true;
    }
}
