package duke.exceptions;

/**
 * {@code InvalidIndexException} is an exception thrown when an
 * index provided by the user is invalid.
 */
public class InvalidIndexException extends DukeException {
    /**
     * Constructs an invalid index exception.
     *
     * @param message the message to be displayed to the users in the event of the exception.
     */
    public InvalidIndexException(String message) {
        super(message);
    }
}
