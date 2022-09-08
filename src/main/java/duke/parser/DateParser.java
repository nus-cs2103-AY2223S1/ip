package duke.parser;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * A parser to parse a String to Local Date and reverse.
 */
public class DateParser {
    private static final DateTimeFormatter dateFormatter = DateTimeFormatter.ISO_DATE;

    /**
     * Convert a String with format yyyy-mm-dd to Local Date.
     * @param date
     * @return
     */
    public static LocalDate convertToLocalDate(String date) {
        return LocalDate.parse(date, dateFormatter);
    }

    /**
     * Convert a Local Date to a String with format MMM dd yyyy.
     * @param date
     * @return
     */
    public static String convertDateToString(LocalDate date) {
        return date.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
    }

    /**
     * Check if a given String is valid to parse to Local Date.
     * @param dateString
     * @return
     */
    public static boolean isDateValid(String dateString) {
        try {
            LocalDate.parse(dateString, dateFormatter);
        } catch (DateTimeParseException e) {
            return false;
        }
        return true;
    }
}
