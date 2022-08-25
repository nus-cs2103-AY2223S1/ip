package processor;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;

import duke.DukeException;

/**
 * Represents a formatter that process a datetime object
 */
public class TaskDatetimeFormatter {
    public static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("uuuu-M-d").withResolverStyle(ResolverStyle.STRICT);

    /**
     * Returns a <code>LocalDate</code> of the converted <code>String</code>
     *
     * @param datetimeString a <code>String</code> to convert
     * @return <code>LocalDate</code>
     * @throws DukeException
     */
    public static LocalDate stringToDatetime(String datetimeString) throws DukeException {
        try {
            return LocalDate.parse(datetimeString, formatter);
        } catch (DateTimeParseException e) {
            throw DukeException.datetimeFormatErrorMessage(datetimeString);
        }
    }
}
