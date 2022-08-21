package utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DukeFormatter {
    public static String formatDate(LocalDate date) {
        final String dateFormatter = "MMM d yyyy";
        return date.format(DateTimeFormatter.ofPattern(dateFormatter));
    }
}
