package Duke.exceptions;

/**
 * {@code InvalidDateException} is an exception thrown when a
 * dateString provided by the user is invalid.
 */
public class InvalidDateException extends DukeException {
    /**
     * This exception is thrown when a user provided an invalid date.
     * The date is likely to be of a wrong format.
     */
    public InvalidDateException() {
        super("Please include a valid date string");
    }
}
