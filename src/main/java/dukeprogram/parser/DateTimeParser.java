package dukeprogram.parser;

import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;

public class DateTimeParser {
    private final static int CURRENT_YEAR = LocalDateTime.now().getYear();

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

    public static LocalDateTime parse(String dateTime) {
        for (DateTimeFormatter format : DATE_TIME_FORMATTERS) {
            try {
                return LocalDateTime.parse(dateTime, format);
            } catch (DateTimeException ignored) {}
        }
        return null;
    }
}
