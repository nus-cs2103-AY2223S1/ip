package duke;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;

public class DateHandler {
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

    static LocalDateTime parseDateString(String dateString) {
        LocalDateTime dateTime = LocalDateTime.parse(dateString, getDateTimeFormatter());
        return dateTime;
    }
}
