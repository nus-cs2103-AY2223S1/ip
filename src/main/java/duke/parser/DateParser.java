package duke.parser;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.TemporalAccessor;

/**
 * Represents a parser that translates between strings of date/time and LocalDateTime objects.
 */
public class DateParser {
    // DateTimeFormatter for the formats of date/time
    private static final DateTimeFormatter FORMAT = new DateTimeFormatterBuilder()
            .appendOptional(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm"))
            .appendOptional(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"))
            .appendOptional(DateTimeFormatter.ofPattern("dd-MM-yyyy"))
            .appendOptional(DateTimeFormatter.ofPattern("dd/MM/yyyy"))
            .appendOptional(DateTimeFormatter.ofPattern("dd MM yyyy HH:mm"))
            .appendOptional(DateTimeFormatter.ofPattern("dd MM yyyy"))
            .toFormatter();

    /**
     * Converts a String of date to LocalDate object
     *
     * @param date The String representation of the date.
     * @return The LocalDate object of the date.
     * @throws DateTimeException if the text cannot be parsed
     */
    public static LocalDateTime parseToDate(String date) throws DateTimeException {
        // @@author HowSuen-reused
        // Reused from https://stackoverflow.com/questions/48280447/java-8-datetimeformatter-with-optional-part
        // with minor modifications.
        LocalDateTime dateTime;
        TemporalAccessor temporalAccessor = DateParser.FORMAT.parseBest(date, LocalDateTime::from, LocalDate::from);
        if (temporalAccessor instanceof LocalDateTime) {
            dateTime = (LocalDateTime) temporalAccessor;
        } else {
            dateTime = ((LocalDate) temporalAccessor).atStartOfDay();
        }
        return dateTime;
        // @@author
    }

    /**
     * Converts a LocalDate object to the String representation.
     *
     * @param date The LocalDate object of the date.
     * @return The String representation of the date.
     */
    public static String dateToString(LocalDateTime date) {
        DateTimeFormatter stringFormat = DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm");
        return date.format(stringFormat);
    }

    /**
     * Converts a LocalDate object to command for save file.
     *
     * @param date The LocalDate object of the date.
     * @return The String representation of the command.
     */
    public static String dateToCommand(LocalDateTime date) {
        DateTimeFormatter stringFormat = DateTimeFormatter.ofPattern("dd MM yyyy HH:mm");
        return date.format(stringFormat);
    }
}
