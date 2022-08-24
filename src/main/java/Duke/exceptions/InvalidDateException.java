package Duke.exceptions;

/**
 * {@code InvalidDateException} is an exception thrown when a
 * dateString provided by the user is invalid.
 */
public class InvalidDateException extends DukeException {
    public InvalidDateException() {
        super("Please include a valid date string");
    }
}
