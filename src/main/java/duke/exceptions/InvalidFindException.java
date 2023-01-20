package duke.exceptions;

/**
 * {@code InvalidFindException} is an exception thrown when a
 * search string provided by the user is invalid.
 */
public class InvalidFindException extends DukeException {
    /**
     * Constructs an invalid find exception.
     */
    public InvalidFindException() {
        super("Please include a valid search text");
    }
}