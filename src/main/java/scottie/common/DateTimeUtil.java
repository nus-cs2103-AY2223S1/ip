package scottie.common;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;

/**
 * A utility class containing methods for parsing and formatting dates and times.
 */
public final class DateTimeUtil {
    // Used for user input and saving to storage
    private static final DateTimeFormatter COMPACT_DATE_TIME_FORMATTER =
            DateTimeFormatter.ofPattern("d/M/yy[ HHmm]");
    // Used for displaying to user
    private static final DateTimeFormatter PRETTY_DATE_TIME_FORMATTER =
            DateTimeFormatter.ofPattern("MMM d yyyy[ h:mma]");

    /**
     * Parses a dateTimeString in the compact format.
     * Returns either a LocalDateTime or a LocalDate depending on whether the
     * input string contains a time.
     *
     * @param dateTimeString The string to be parsed.
     * @return The TemporalAccessor resulting from parsing the input string.
     */
    public static TemporalAccessor parseCompactDateTime(String dateTimeString) {
        return COMPACT_DATE_TIME_FORMATTER.parseBest(dateTimeString, LocalDateTime::from, LocalDate::from);
    }

    /**
     * Formats a TemporalAccessor as a string in the compact format.
     *
     * @param dateTime The TemporalAccessor to format.
     * @return The formatted string.
     */
    public static String formatCompactDateTime(TemporalAccessor dateTime) {
        return COMPACT_DATE_TIME_FORMATTER.format(dateTime);
    }

    /**
     * Formats a TemporalAccessor as a string in the pretty format.
     *
     * @param dateTime The TemporalAccessor to format.
     * @return The formatted string.
     */
    public static String formatPrettyDateTime(TemporalAccessor dateTime) {
        return PRETTY_DATE_TIME_FORMATTER.format(dateTime);
    }
}
