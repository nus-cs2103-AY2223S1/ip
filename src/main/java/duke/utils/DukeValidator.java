package duke.utils;

import java.time.LocalDate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import duke.exceptions.DukeException;

/**
 * Encapsulates the logic for handling validation in various parts of the application.
 *
 * @author Emily Ong Hui Qi
 */
public class DukeValidator {
    /**
     * Regex pattern matcher for a date in yyyy-mm-dd format.
     */
    private static final Pattern MATCH_DATE = Pattern.compile(
            "^(?<year>\\d{4})-(?<month>0[0-9]|1[0-2])-(?<day>0[0-9]|1[0-9]|2[0-9]|3[0-1])$"
    );

    private static final String ERROR_INVALID_DATE = "Date is not in the specified yyyy-mm-dd format!";

    /**
     * Returns the {@link LocalDate} object parsed from the provided date string.
     *
     * @param date The provided date string.
     *
     * @return The date object corresponding to the provided date string if and only if the provided date string
     *         matches the expected format.
     * @throws DukeException If the provided date string does not match the expected format.
     */
    public static LocalDate parseDate(String date) throws DukeException {
        String strippedDate = date.strip();
        if (strippedDate.length() == 0) {
            return null;
        }
        Matcher matcher = MATCH_DATE.matcher(strippedDate);
        if (!matcher.find()) {
            throw new DukeException(DukeValidator.ERROR_INVALID_DATE);
        }
        return LocalDate.parse(strippedDate);
    }
}
