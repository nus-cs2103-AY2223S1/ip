package jenny.util;

import jenny.exceptions.JennyException;

import java.time.LocalDate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
     * Parse and return a {@link LocalDate} if successful, throws a {@link JennyException} on failure.
     *
     * @param date a string in yyyy-MM-dd format.
     * @return a {@link LocalDate}.
     * @throws JennyException when string is not in yyyy-MM-dd format.
     */
    public static LocalDate parseDate(String date) throws JennyException {
        Matcher matcher = VALID_DATE_PATTERN.matcher(date);
        if (!matcher.find()) {
            throw new JennyException(MESSAGE_SCOPE, ERROR_INVALID_DATE);
        } else {
            return LocalDate.parse(date);
        }
    }
}
