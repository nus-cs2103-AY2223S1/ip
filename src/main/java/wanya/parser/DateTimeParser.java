package wanya.parser;

import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;

//Below code are referenced from
//https://www.geeksforgeeks.org/java-time-format-datetimeformatterbuilder-class-in-java/
/**
 * Represents a parser that parses String to LocalDateTime in various patterns.
 */
public class DateTimeParser {
    // only allow format of yyyy-MM-dd and yyyy-MM-dd HH:mm as of now
    // @@author laxus2308-reused
    // Reused from
    // https://stackoverflow.com/questions/40175196/java-parsing-string-to-localdatetime-without-providing-time
    private static final DateTimeFormatter FORMATTER =  new DateTimeFormatterBuilder()
            .appendPattern("yyyy-MM-dd")
            .optionalStart()
            .appendPattern(" HH:mm")
            .optionalEnd()
            .parseDefaulting(ChronoField.HOUR_OF_DAY, 0)
            .parseDefaulting(ChronoField.MINUTE_OF_HOUR, 0)
            .toFormatter();

    /**
     * Returns LocalDateTime object of the specified date and time.
     * Only accepts string of format "yyyy-MM-dd" or "yyyy-MM-dd HH:mm".
     *
     * @param dateStr String that contains the date and time.
     * @return LocalDateTime object
     * @throws DateTimeException if invalid string format given.
     */
    public static LocalDateTime getDateTime(String dateStr) throws DateTimeException {
        return LocalDateTime.parse(dateStr, FORMATTER);
    }

    /**
     * Returns a String representation of LocalDateTime object.
     * Shows the date and time explicitly for users to understand better.
     *
     * @param dateTime LocalDateTime object of date and time.
     * @return String representation of date and time.
     */
    public static String getDateTimeString(LocalDateTime dateTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy hh:mm a");
        return dateTime.format(formatter);
    }

    /**
     * Encodes a LocalDateTime object to a String representation for saving file.
     *
     * @param dateTime LocalDateTime object of date and time.
     * @return encoded String representation of date and time.
     */
    public static String getDateTimeStorage(LocalDateTime dateTime) {
        return DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm").format(dateTime);
    }
}
