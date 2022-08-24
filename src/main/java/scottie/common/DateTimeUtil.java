package scottie.common;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;

public final class DateTimeUtil {
    // Used for user input and saving to storage
    private static final DateTimeFormatter COMPACT_DATE_TIME_FORMATTER =
            DateTimeFormatter.ofPattern("d/M/yy[ HHmm]");
    // Used for displaying to user
    private static final DateTimeFormatter PRETTY_DATE_TIME_FORMATTER =
            DateTimeFormatter.ofPattern("MMM d yyyy[ h:mma]");

    public static TemporalAccessor parseCompactDateTime(String dateTimeString) {
        return COMPACT_DATE_TIME_FORMATTER.parseBest(dateTimeString, LocalDateTime::from, LocalDate::from);
    }

    public static String formatCompactDateTime(TemporalAccessor dateTime) {
        return COMPACT_DATE_TIME_FORMATTER.format(dateTime);
    }

    public static String formatPrettyDateTime(TemporalAccessor dateTime) {
        return PRETTY_DATE_TIME_FORMATTER.format(dateTime);
    }
}
