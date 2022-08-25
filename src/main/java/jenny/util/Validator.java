package jenny.util;

import jenny.exceptions.JennyException;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

/**
 * Handles input validation for the JennyBot application.
 * CS2103 Week 3
 * AY21/22 Semester 1
 *
 * @author Deon
 */
public class Validator {
    private static final String MESSAGE_SCOPE = Validator.class.getSimpleName();
    public static LocalDate parseDate(String input) {
        try {
            return LocalDate.parse(input);
        } catch (DateTimeParseException e) {
            throw new JennyException(MESSAGE_SCOPE, e.getMessage());
        }
    }
}
