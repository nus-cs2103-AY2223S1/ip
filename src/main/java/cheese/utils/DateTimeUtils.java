package cheese.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateTimeUtils {
    private static final DateTimeFormatter DATETIME_FORMATTER_INPUT =
            DateTimeFormatter.ofPattern("yyyy-dd-MM HH:mm");
    private static final DateTimeFormatter DATETIME_FORMATTER_OUTPUT =
            DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");

    public static LocalDateTime parseString(String dateTime) {
        return LocalDateTime.parse(dateTime, DATETIME_FORMATTER_INPUT);
    }

    public static String parseLocalDateTimeToOutput(LocalDateTime dateTime) {
        return DATETIME_FORMATTER_OUTPUT.format(dateTime);
    }

    public static String parseLocalDateTimeToInput(LocalDateTime dateTime) {
        return DATETIME_FORMATTER_INPUT.format(dateTime);
    }
}
