package exceptions;

/**
 * {@code InvalidSecondaryCommandException} is an exception thrown when a
 * secondary command provided by the user is invalid.
 */
public class InvalidSecondaryCommandException extends DukeException {
    public InvalidSecondaryCommandException(String message) {
        super(message);
    }
}
