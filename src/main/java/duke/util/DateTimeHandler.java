package duke.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import duke.exception.UnexpectedDateTimeFormatException;

/**
 * Handles the Date and Time either from user input or from storage.
 */
public class DateTimeHandler {
    public static final DateTimeFormatter dukeDateTimeFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
    public static final DateTimeFormatter storageDateTimeFormat = DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm");

    /**
     * Formats and stores dateTime as a LocalDateTime object.
     *
     * @param strDateTime Date and Time in the format dd/MM/yyyy HHmm.
     * @return a LocalDateTime object.
     * @throws UnexpectedDateTimeFormatException when strDateTime is not in dd/MM/yyyy HHmm format.
     */
    public static LocalDateTime formatDukeDateTime(String strDateTime) throws UnexpectedDateTimeFormatException {
        try {
            LocalDateTime dateTime = LocalDateTime.parse(strDateTime, dukeDateTimeFormat);
            return dateTime;
        } catch (DateTimeParseException e) {
            throw new UnexpectedDateTimeFormatException();
        }
    }

    /**
     * Formats and stores dateTime as a LocalDateTime object.
     *
     * @param strDateTime Date and Time in the format MMM dd yyyy HH:mm.
     * @return a LocalDateTime object.
     */
    public static LocalDateTime formatStorageDateTime(String strDateTime) {
        LocalDateTime dateTime = LocalDateTime.parse(strDateTime, storageDateTimeFormat);
        return dateTime;
    }

}
