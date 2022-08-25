package parser;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;

public class TaskDatetimeFormatter {
    public static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("uuuu-M-d").withResolverStyle(ResolverStyle.STRICT);

    public static LocalDate stringToDatetime(String datetimeString) throws DukeException {
        try {
            return LocalDate.parse(datetimeString, formatter);
        } catch (DateTimeParseException e) {
            throw DukeException.datetimeFormatErrorMessage(datetimeString);
        }
    }
}
