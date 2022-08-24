package Duke.exceptions;

/**
 * {@code InvalidSecondaryCommandException} is an exception thrown when a
 * secondary command provided by the user is invalid.
 */
public class InvalidSecondaryCommandException extends DukeException {
    /**
     * This exception is thrown when a user provided an invalid secondary command.
     * The secondary command may not exist, contain a typo or is appended with other
     * values without a spacing.
     *
     * @param message Is a string that will be displayed to the user.
     */
    public InvalidSecondaryCommandException(String message) {
        super(message);
    }
}
