package exceptions;

/**
 * InvalidCommandException is thrown when no such command is valid
 */
public class InvalidCommandException extends Exception {

    /**
     * Creates a new InvalidCommandException
     */
    public InvalidCommandException(String msg) {
        super(msg);
    }
}
