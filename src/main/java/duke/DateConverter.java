package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Converts string to date and time form.
 */
public class DateConverter {
    private static final DateTimeFormatter dateFormatter = DateTimeFormatter.ISO_LOCAL_DATE;

    /**
     * Convert LocalDate to MMM dd yyyy format.
     * @param date LocalDate to be converted.
     * @return String of form MMM dd yyyy.
     */
    public static String convertToString(LocalDate date) {
        return date.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
    }

    /**
     * Checks if dateString is in valid form.
     * @param dateString String contains date.
     * @return true if dateString is in valid form, else otherwise.
     */
    public static boolean isValidDate(String dateString) {
        try {
            LocalDate.parse(dateString, dateFormatter);
        } catch (DateTimeParseException e) {
            return false;
        }
        return true;
    }
}
