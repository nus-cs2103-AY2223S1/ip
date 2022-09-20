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
                    .optionalStart()
                    .appendPattern("d MMM")
                    .optionalEnd()

                    .optionalStart()
                    .appendPattern("d MM")
                    .optionalEnd()

                    .optionalStart()
                    .appendPattern("d/MM")
                    .optionalEnd()

                    .optionalStart()
                    .appendPattern("MMM d")
                    .optionalEnd()

                    .optionalStart()
                    .appendPattern(" yy")
                    .optionalEnd()

                    .optionalStart()
                    .appendPattern(" H:mm")
                    .optionalEnd()

                    .optionalStart()
                    .appendPattern(" h:mm a")
                    .optionalEnd()

                    .parseDefaulting(ChronoField.HOUR_OF_DAY, 12)
                    .parseDefaulting(ChronoField.MINUTE_OF_HOUR, 0)
                    .parseDefaulting(ChronoField.YEAR, CURRENT_YEAR)
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
                System.out.println("Skipping " + format);
            }
        }
        return null;
    }
}
