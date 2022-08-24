package Duke.exceptions;

/**
 * {@code InvalidCommandException} is an exception thrown when a
 * command provided by the user is invalid.
 */
public class InvalidCommandException extends DukeException {
    /**
     * This exception is thrown when a user provided an invalid command.
     * The command may not exist, contain a typo or is appended with other
     * values without a spacing.
     *
     * @param message Is a string that will be displayed to the user.
     */
    public InvalidCommandException(String message) {
        super(message);
    }
}
