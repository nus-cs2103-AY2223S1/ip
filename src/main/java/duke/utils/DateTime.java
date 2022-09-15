package duke.utils;

import java.time.format.DateTimeFormatter;

/**
 * Stores the format of DateTime that the entire project follows.
 */
public class DateTime {
    public static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
}
