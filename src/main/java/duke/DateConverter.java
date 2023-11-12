package duke;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Converts string to date and time form.
 */
public class DateConverter {
    private static final DateTimeFormatter dateFormatter = DateTimeFormatter.ISO_LOCAL_DATE;

    /**
     * Converts LocalDate to string of MMM dd yyyy format.
     * @param date LocalDate to be converted.
     * @return String of form MMM dd yyyy.
     */
    public static String convertDateToString(LocalDate date) {
        return date.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
    }

    /**
     * Converts LocalDateTime to string of mmm yyyy hh:mm format.
     * @param time LocalDateTime to be converted.
     * @return String of form mmm yyyy hh:mm.
     */
    public static String convertTimeToString(LocalDateTime time) {
        return time.format(DateTimeFormatter.ofPattern("d MMM yyyy HH:mm a"));
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

    /**
     * Checks if timeString is in valid form.
     * @param timeString String contains time.
     * @return true if timeString is in valid form, else otherwise.
     */
    public static boolean isValidTime(String timeString) {
        try {
            timeString = timeString.replace(" ", "T");
            LocalDateTime.parse(timeString);
        } catch (DateTimeParseException e) {
            return false;
        }
        return true;
    }
}
