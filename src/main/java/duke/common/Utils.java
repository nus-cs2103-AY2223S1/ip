package duke.common;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

/**
 * Utility methods.
 */
public class Utils {

    /**
     * Parses the string and converts to a LocalDate object
     *
     * @param inputString string to be converted to date (format: yyyy-MM-dd)
     * @return LocalDate converted from the given string
     */
    public static LocalDate parseDate(String inputString) {
        return LocalDate.parse(inputString);
    }

    /**
     * Converts the given date to specified format string.
     *
     * @param date LocalDate to be converted
     * @return date in a different string
     */
    public static String convertLocalDate(LocalDate date) {
        return date.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.LONG));
    }
}
