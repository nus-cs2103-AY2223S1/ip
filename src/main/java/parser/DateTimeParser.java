package parser;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * <h1>DateTimeParser class</h1>
 * Parses the input String from the user and generates a
 * LocalDateTime with an appropriate format.
 */
public class DateTimeParser {
    private static DateTimeFormatter parsingFormatter = java.time.format
            .DateTimeFormatter.ofPattern("MMM d yyyy hh:mm a");
    private static DateTimeFormatter readingFormatter = java.time.format
            .DateTimeFormatter.ofPattern("d-MMM-yyyy hh:mm a");

    /**
     * Returns the LocalDateTime parsed from the input String.
     * The LocalDateTime has to be of the format 'MMM d yyyy hh:mm a'.
     *
     * @param str String representing the LocalDateTime to be returned.
     * @return LocalDateTime parsed from the input String.
     */
    public static LocalDateTime changeStringToParsingDateTime(String str) {
        return LocalDateTime.parse(str, DateTimeParser.parsingFormatter);
    }

    /**
     * Returns the LocalDateTime parsed from the input String.
     * The LocalDateTime has to be of the format 'd-MMM-yyyy hh:mm a'.
     *
     * @param str String representing the LocalDateTime to be returned.
     * @return LocalDateTime parsed from the input String.
     */
    public static LocalDateTime changeStringToReadingDateTime(String str) {
        return LocalDateTime.parse(str, DateTimeParser.readingFormatter);
    }

    /**
     * Returns the String representation of the reformatted input LocalDateTime.
     * The new LocalDateTime is of the format 'd-MMM-yyyy hh:mm a'.
     *
     * @param localDateTime to be reformatted.
     * @return the reformatted String of the LocalDateTime.
     */
    public static String changeDateTimeFormat(LocalDateTime localDateTime) {
        return localDateTime.format(DateTimeFormatter.ofPattern("d-MMM-yyyy hh:mm a"));
    }
}
