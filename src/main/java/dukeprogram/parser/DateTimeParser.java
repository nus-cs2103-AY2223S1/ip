package dukeprogram.parser;

import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;

/**
 * DateTimeParser is able to convert several string date formats to
 * actual LocalDateTime objects.
 */
public class DateTimeParser {
    private static final int CURRENT_YEAR = LocalDateTime.now().getYear();

    private static final DateTimeFormatter[] DATE_TIME_FORMATTERS = {
            new DateTimeFormatterBuilder()
                    .parseCaseInsensitive()
                    .appendPattern("d MMM")

                    .optionalStart()
                    .appendPattern(" yyyy")
                    .optionalEnd()

                    .optionalStart()
                    .appendPattern(" HH mm")
                    .optionalEnd()

                    .optionalStart()
                    .appendPattern(" HH:mm")
                    .optionalEnd()

                    .optionalStart()
                    .appendPattern(" hh:mm a")
                    .optionalEnd()

                    .parseDefaulting(ChronoField.HOUR_OF_DAY, 12)
                    .parseDefaulting(ChronoField.MINUTE_OF_HOUR, 0)
                    .parseDefaulting(ChronoField.YEAR, CURRENT_YEAR)
                    .toFormatter(),

            new DateTimeFormatterBuilder()
                    .parseCaseInsensitive()
                    .appendPattern(" MMM d")

                    .optionalStart()
                    .appendPattern(" yyyy")
                    .optionalEnd()

                    .optionalStart()
                    .appendPattern(" HHmm")
                    .optionalEnd()

                    .optionalStart()
                    .appendPattern(" HH:mm")
                    .optionalEnd()

                    .optionalStart()
                    .appendPattern(" hh:mm a")
                    .optionalEnd()

                    .parseDefaulting(ChronoField.HOUR_OF_DAY, 12)
                    .parseDefaulting(ChronoField.MINUTE_OF_HOUR, 0)
                    .parseDefaulting(ChronoField.YEAR, CURRENT_YEAR)
                    .toFormatter(),

            new DateTimeFormatterBuilder()
                    .appendPattern("d MM")

                    .optionalStart()
                    .appendPattern(" yyyy")
                    .optionalEnd()

                    .optionalStart()
                    .appendPattern(" HHmm")
                    .optionalEnd()

                    .optionalStart()
                    .appendPattern(" HH:mm")
                    .optionalEnd()

                    .optionalStart()
                    .appendPattern(" hh:mm a")
                    .optionalEnd()

                    .parseDefaulting(ChronoField.HOUR_OF_DAY, 12)
                    .parseDefaulting(ChronoField.MINUTE_OF_HOUR, 0)
                    .parseDefaulting(ChronoField.YEAR, CURRENT_YEAR)
                    .toFormatter(),

            new DateTimeFormatterBuilder()
                    .appendPattern("yyyy-MM-dd")

                    .optionalStart()
                    .appendPattern(" HHmm")
                    .optionalEnd()

                    .optionalStart()
                    .appendPattern(" HH:mm")
                    .optionalEnd()

                    .optionalStart()
                    .appendPattern(" hh:mm a")
                    .optionalEnd()

                    .parseDefaulting(ChronoField.HOUR_OF_DAY, 12)
                    .parseDefaulting(ChronoField.MINUTE_OF_HOUR, 0)
                    .toFormatter()
    };

    /**
     * Formats a dateTime string to a LocalDateTime object if it is within
     * any of the possible string formats. Otherwise, returns nothing.
     * @param dateTime a date time expressed as a string
     * @return either a LocalDateTime object if the string is valid, or null if it is not valid.
     */
    public static LocalDateTime parse(String dateTime) {
        for (DateTimeFormatter format : DATE_TIME_FORMATTERS) {
            try {
                return LocalDateTime.parse(dateTime, format);
            } catch (DateTimeException ignored) {
                return null;
            }
        }
        return null;
    }
}
