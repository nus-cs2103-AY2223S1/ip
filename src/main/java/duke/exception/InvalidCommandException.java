package duke.exception;

/**
 * Represents an exception when the user enters an invalid command.
 */
public class InvalidCommandException extends Exception {
    public InvalidCommandException(String message) {
        super(message);
    }
}
