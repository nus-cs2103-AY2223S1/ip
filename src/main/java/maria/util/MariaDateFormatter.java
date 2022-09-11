package maria.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Formats the date to the desired display format.
 */
public class MariaDateFormatter {

    public static String formatDisplay(LocalDate ld) {
        return ld.format(DateTimeFormatter.ofPattern("d MMMM yyyy"));
    }

    public static String formatStorage(LocalDate ld) {
        return ld.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }

}
