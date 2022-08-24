package Duke.exceptions;

/**
 * {@code InvalidIndexException} is an exception thrown when an
 * index provided by the user is invalid.
 */
public class InvalidIndexException extends DukeException {
    /**
     * This exception is thrown when a user provided an invalid index.
     *
     * @param message Is a string that will be displayed to the user.
     */
    public InvalidIndexException(String message) {
        super(message);
    }
}
