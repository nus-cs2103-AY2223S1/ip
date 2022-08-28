package duke.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Class to convert between LocalDate and String objects.
 *
 * @author Kavan
 */
public abstract class DateAndTimeParser {
    /**
     * Returns dateTime in 'MMM dd uuuu' format (e.g. Oct 15 2019).
     *
     * @param dateTime LocalDate object.
     * @return dateTime as a String object.
     */
    public static String convertDate(LocalDate dateTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd uuuu");
        return dateTime.format(formatter);
    }

    /**
     * Returns dateTime as a LocalDate object.
     *
     * @param dateTime dateTime as a String.
     * @return dateTime as a LocalDate object.
     */
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
