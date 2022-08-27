package duke;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;

/**
 * Represents a handler for dates
 */
public class DateHandler {
    /**
     * Returns a pre-configured DateTimeFormatter
     * @return a DateTimeFormatter instance
     * @see DateTimeFormatterBuilder
     */
    public static DateTimeFormatter getDateTimeFormatter() {
        DateTimeFormatterBuilder builder = new DateTimeFormatterBuilder();
        builder.appendOptional(DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"))
                .appendOptional(DateTimeFormatter.ofPattern("yyyy/MM/dd HHmm"))
                .appendOptional(DateTimeFormatter.ofPattern("HHmm dd/MM/yyyy"))
                .appendOptional(DateTimeFormatter.ofPattern("HHmm yyyy-MM-dd"))
                .appendOptional(DateTimeFormatter.ofPattern("HHmm yyyy/MM/dd"))
                .appendOptional(DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm"))
                .appendOptional(DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm"))
                .appendOptional(DateTimeFormatter.ofPattern("HHmm dd-MM-yyyy"))
                .appendOptional(DateTimeFormatter.ofPattern("MMM dd yyyy HHmm"))
                .appendOptional(DateTimeFormatter.ofPattern("HHmm dd MMM yyyy"))
                .appendOptional(DateTimeFormatter.ofPattern("dd MMM yyyy HHmm"));
        return builder.toFormatter();
    }

    /**
     * Returns an instance of LocalDateTime from conversion of dateString to LocalDateTime
     * @param dateString
     * @return a LocalDateTime object
     * @see LocalDateTime
     */
    public static LocalDateTime parseDateString(String dateString) {
        LocalDateTime dateTime = LocalDateTime.parse(dateString, getDateTimeFormatter());
        return dateTime;
    }
}
