package duke;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.Objects;

/**
 * Parses Date from String
 */
public class DateParser {
    /**
     * Takes the date string and parses it into {@link LocalDate} object
     * @param date
     * @return {@link LocalDate} object for the date string
     */
    public static LocalDate parseDate(String date) {
        assert !Objects.equals(date, "") : "Date cannot be null";
        try {
            LocalDate parsedDate = LocalDate.parse(date);
            return parsedDate;
        } catch (DateTimeException e) {
            throw e;
        }
    }
}
