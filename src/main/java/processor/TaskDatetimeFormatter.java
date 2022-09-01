package processor;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;

import tobtob.TobTobException;

/**
 * Represents a formatter that process a datetime object
 */
public class TaskDatetimeFormatter {
    private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("uuuu-M-d")
            .withResolverStyle(ResolverStyle.STRICT);

    /**
     * Returns a {@link LocalDate} of the converted {@link String}.
     *
     * @param datetimeString a {@link String} to convert
     * @return {@link LocalDate}
     * @throws TobTobException If {@code datetimeString} doesn't follow the format.
     */
    public static LocalDate stringToDatetime(String datetimeString) throws TobTobException {
        try {
            return LocalDate.parse(datetimeString, formatter);
        } catch (DateTimeParseException e) {
            throw TobTobException.datetimeFormatErrorMessage(datetimeString);
        }
    }
}
