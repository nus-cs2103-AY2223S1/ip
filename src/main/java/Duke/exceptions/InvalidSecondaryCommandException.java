package Duke.exceptions;

/**
 * {@code InvalidSecondaryCommandException} is an exception thrown when a
 * secondary command provided by the user is invalid.
 */
public class InvalidSecondaryCommandException extends DukeException {
    /**
     * The constructor for an invalid secondary command exception.
     *
     * @param message the message to be displayed to the users in the event of the exception.
     */
    public InvalidSecondaryCommandException(String message) {
        super(message);
    }
}
