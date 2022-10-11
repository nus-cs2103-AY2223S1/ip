package duke.exceptions;

/**
 * {@code InvalidCommandException} is an exception thrown when a
 * command provided by the user is invalid.
 */
public class InvalidCommandException extends DukeException {
    /**
     * Constructs an invalid command exception.
     */
    public InvalidCommandException(String message) {
        super(message);
    }
}
