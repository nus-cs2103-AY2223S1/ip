package exceptions;

/**
 * {@code InvalidIndexException} is an exception thrown when an
 * index provided by the user is invalid.
 */
public class InvalidIndexException extends DukeException {
    public InvalidIndexException(String message) {
        super(message);
    }
}
