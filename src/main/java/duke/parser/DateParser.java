package duke.parser;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * A parser to parse a String to Local Date and reverse.
 */
public class DateParser {
    private static final DateTimeFormatter dateFormatter = DateTimeFormatter.ISO_DATE;
    private static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    /**
     * Convert a String with format yyyy-mm-dd to Local Date.
     * @param date
     * @return
     */
    public static LocalDate convertToLocalDate(String date) {
        return LocalDate.parse(date, dateFormatter);
    }

    public static LocalDateTime convertToLocalDateTime(String dateTime) {
        return LocalDateTime.parse(dateTime, dateTimeFormatter);
    }
    /**
     * Convert a Local Date to a String with format MMM dd yyyy.
     * @param date
     * @return
     */
    public static String convertDateToString(LocalDate date) {
        return date.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
    }

    public static String convertDateTimeToString(LocalDateTime dateTime) {
        return dateTime.format(DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm"));
    }

    public static String convertDateToMemoryString(LocalDate date) {
        return date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }

    public static String convertDateTimeToMemoryString(LocalDateTime dateTime) {
        return dateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
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
