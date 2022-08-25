package Duke.exceptions;

/**
 * {@code InvalidDateException} is an exception thrown when a
 * dateString provided by the user is invalid.
 */
public class InvalidDateException extends DukeException {
    /**
     * The constructor for an invalid date exception.
     */
    public InvalidDateException() {
        super("Please include a valid date string");
    }
}
