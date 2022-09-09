package parser;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * <h1>DateTimeParser class</h1>
 * Parses the input String from the user and generates a
 * LocalDateTime with an appropriate format.
 */
public class DateTimeParser {
    private static final String DATE_TIME_READING_FORMAT = "d-MMM-yyyy hh:mm a";
    private static final String DATE_TIME_PARSING_FORMAT = "MMM d yyyy hh:mm a";
    private static final DateTimeFormatter parsingDateTimeFormatter = java.time.format
            .DateTimeFormatter.ofPattern(DATE_TIME_PARSING_FORMAT);
    private static final String DATE_PARSING_FORMAT = "MMM d yyyy";
    private static final DateTimeFormatter parsingDateFormatter = java.time.format
            .DateTimeFormatter.ofPattern(DATE_PARSING_FORMAT);
    private static final DateTimeFormatter readingFormatter = java.time.format
            .DateTimeFormatter.ofPattern(DATE_TIME_READING_FORMAT);

    /**
     * Returns the LocalDateTime parsed from the input String.
     * The LocalDateTime has to be of the format 'MMM d yyyy hh:mm a'.
     *
     * @param str String representing the LocalDateTime to be returned.
     * @return LocalDateTime parsed from the input String.
     */
    public static LocalDateTime changeStringToParsingDateTime(String str) {
        return LocalDateTime.parse(str, DateTimeParser.parsingDateTimeFormatter);
    }

    /**
     * Returns the LocalDate parsed from the input String.
     * The LocalDate has to be of the format 'MMM d yyyy'.
     *
     * @param str String representing the LocalDate to be returned.
     * @return LocalDate parsed from the input String.
     */
    public static LocalDate changeStringToParsingDate(String str) {
        return LocalDate.parse(str, DateTimeParser.parsingDateFormatter);
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
    public static String getReadingLocalDateTimeString(LocalDateTime localDateTime) {
        return localDateTime.format(DateTimeFormatter.ofPattern(DATE_TIME_READING_FORMAT));
    }
}
