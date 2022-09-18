package sus.common;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.FormatStyle;

import sus.SusException;

/**
 * Utility methods.
 */
public class Utils {

    /**
     * Parses the string and converts to a LocalDate object.
     *
     * @param inputString string to be converted to date (format: yyyy-MM-dd)
     * @return LocalDate converted from the given string
     */
    public static LocalDate parseDate(String inputString) throws SusException {
        try {
            return LocalDate.parse(inputString);
        } catch (DateTimeParseException ignored) {
            throw new SusException(Messages.MESSAGE_WRONG_DATE_FORMAT);
        }
    }

    /**
     * Converts the given date to specified format string.
     *
     * @param date LocalDate to be converted
     * @return date in a different string
     */
    public static String convertLocalDateToString(LocalDate date) {
        return date.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.LONG));
    }
}
