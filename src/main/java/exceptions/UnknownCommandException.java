package exceptions;

/**
 * Exception when the user inputs a command that is not recognised.
 */
public class UnknownCommandException extends Exception {
    /**
     * Constructor for this exception.
     */
    public UnknownCommandException() {
        super("I don't get what you are saying...");
    }
}
