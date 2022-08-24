package duke.utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * TODO: Add JavaDocs
 */
public class DukeFormatter {
    /**
     * TODO: Add JavaDocs
     */
    public static String formatDate(LocalDate date) {
        final String dateFormatter = "MMM d yyyy";
        return date.format(DateTimeFormatter.ofPattern(dateFormatter));
    }
}
