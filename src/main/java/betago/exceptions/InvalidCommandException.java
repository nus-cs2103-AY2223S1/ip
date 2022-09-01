package betago.exceptions;

/**
 * InvalidCommandException that is thrown when the user provides an invalid command.
 */
public class InvalidCommandException extends Exception {
    public InvalidCommandException(String message) {
        super(message);
    }
}
