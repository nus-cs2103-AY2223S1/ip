package Duke.exceptions;

/**
 * {@code InvalidFindException} is an exception thrown when a
 * search string provided by the user is invalid.
 */
public class InvalidFindException extends DukeException {
    public InvalidFindException() {
        super("Please include a valid search text");
    }
}