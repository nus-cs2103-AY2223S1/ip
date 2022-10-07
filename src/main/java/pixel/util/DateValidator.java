package pixel.util;

import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Checks whether the format of a due is in valid date-time format
 * in yyyy-MM-dd HHmm
 */
public class DateValidator {
    private final DateTimeFormatter dateTimeFormatter;

    /**
     * Constructor for a new DateValidator object
     *
     * @param dateFormatter determines the accepted date&time format of the input string
     */
    public DateValidator(DateTimeFormatter dateFormatter) {
        this.dateTimeFormatter = dateFormatter;
    }

    /**
     * Checks if the input string matches the accepted date&time format of the formatter
     *
     * @return Whether date&time format is valid
     */
    public boolean isValid(String dateStr) {
        try {
            this.dateTimeFormatter.parse(dateStr);
        } catch (DateTimeParseException e) {
            return false;
        }
        return true;
    }
}
