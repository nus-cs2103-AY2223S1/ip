package jenny.util;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import jenny.exceptions.JennyException;


/**
 * Handles input validation for the JennyBot application.
 * CS2103 Week 3
 * AY21/22 Semester 1
 *
 * @author Deon
 */
public class Validator {
    private static final String MESSAGE_SCOPE = Validator.class.getSimpleName();
    private static final Pattern VALID_DATE_PATTERN = Pattern.compile(
        "^(?<year>\\d{4})-(?<month>0[0-9]|1[0-2])-(?<day>0[0-9]|1[0-9]|2[0-9]|3[0-1])$"
    );
    private static final String ERROR_INVALID_DATE = "Date is not in yyyy-MM-dd format.";

    /**
     * Checks if a date is valid and parses it into a {@link LocalDate}.
     *
     * @param date a string in {@code "yyyy-MM-dd"} format.
     * @return a {@link LocalDate}.
     * @throws JennyException when runtime exceptions are thrown in the JennyBot application.
     */
    public static LocalDate parseDate(String date) throws JennyException {
        Matcher matcher = VALID_DATE_PATTERN.matcher(date);
        if (!matcher.find()) {
            throw new JennyException(MESSAGE_SCOPE, ERROR_INVALID_DATE);
        }
        try {
            return LocalDate.parse(date);
        } catch (DateTimeParseException e) {
            throw new JennyException(MESSAGE_SCOPE, e.getMessage());
        }
    }
}
