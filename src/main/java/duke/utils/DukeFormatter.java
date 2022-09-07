package duke.utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Encapsulates the logic for handling formatting in various parts of the application.
 *
 * @author Emily Ong Hui Qi
 */
public class DukeFormatter {
    /**
     * Returns the formatted date according to the {@code MMM d yyyy} format.
     *
     * @param date The date object.
     *
     * @return The formatted date.
     */
    public static String formatDate(LocalDate date) {
        final String dateFormatter = "MMM d yyyy";
        return date.format(DateTimeFormatter.ofPattern(dateFormatter));
    }
}
