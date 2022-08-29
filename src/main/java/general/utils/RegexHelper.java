package general.utils;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * A utility class containing useful methods to parse dates and times from a given string.
 *
 * @author Richard Dominick
 */
public class RegexHelper {
    // TODO: Make regex validate months date according to month length
    public static final Pattern DATE_REGEX = Pattern.compile("\\b" + // word boundary
                    "(?:[1-9]|[0-2]\\d|3[0-1])" + // 1-31, 01-31 accepted
                    " " +
                    "(?:JAN(?:UARY)?" + // both 3-letter and full month names are accepted
                    "|FEB(?:RUARY)?" +
                    "|MAR(?:CH)?" +
                    "|APR(?:IL)?" +
                    "|MAY" +
                    "|JUN(?:E)?" +
                    "|JUL(?:Y)?" +
                    "|AUG(?:UST)?" +
                    "|SEP(?:TEMBER)?" +
                    "|OCT(?:OBER)?" +
                    "|NOV(?:EMBER)?" +
                    "|DEC(?:EMBER)?)" +
                    " " +
                    "\\d{4}" + // all 4 digit years are accepted
                    "(?=\\b|$)",
            Pattern.CASE_INSENSITIVE); // word boundary (lookahead)

    // TODO: Differentiate lack of separator from 4-digit year
    public static final Pattern TIME_REGEX = Pattern.compile("\\b" + // word boundary
            "(?:\\d|[0-1]\\d|2[0-3])" + // hour (1-2 digits, 24h format from 0 to 23)
            "[:.]" + // hour-minute separator (non-optional until above TODO is done)
            "[0-5]\\d" + // minute (always 2 digits)
            "(?=h\\b|\\b|$)"); // word boundary or optional 'h' time unit (lookahead)

    /**
     * Extracts and parses the first date from an input string (which contains the date).
     *
     * @param text The input string to extract the date from
     * @return an optional LocalDate object with the date specified in the string
     */
    public static Optional<LocalDate> extractAndParseDate(String text) {
        final Matcher matcher = DATE_REGEX.matcher(text);
        if (!matcher.find()) {
            return Optional.empty();
        }
        // Replace full month with 3-letter equivalent so that DateTimeFormatter
        // can parse it correctly.
        final String extractedDate = matcher.group()
                .replaceFirst("(?<=\\s[a-zA-Z]{3})[a-zA-Z]+(?=\\s)", "");
        final DateTimeFormatter formatter = new DateTimeFormatterBuilder()
                .parseCaseInsensitive()
                .appendPattern("d MMM yyyy")
                .toFormatter();
        return Optional.of(LocalDate.parse(extractedDate, formatter));
    }

    /**
     * Extracts and parses the first time from an input string (which contains the time).
     *
     * @param text The input string to extract the time from
     * @return an optional LocalTime object with the time specified in the string
     */
    public static Optional<LocalTime> extractAndParseTime(String text) {
        final Matcher matcher = TIME_REGEX.matcher(text);
        if (!matcher.find()) {
            return Optional.empty();
        }
        // Standardise hour-minute separator so that DateTimeFormatter
        // can parse it correctly.
        final String extractedTime = matcher.group()
                .replaceFirst("[:.]", ":");
        final DateTimeFormatter formatter = new DateTimeFormatterBuilder()
                .parseCaseInsensitive()
                .appendPattern("HH:mm")
                .toFormatter();
        return Optional.of(LocalTime.parse(extractedTime, formatter));
    }
}
