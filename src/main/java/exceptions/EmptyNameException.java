package exceptions;

/**
 * Exception when the user created a task without a name.
 */
public class EmptyNameException extends Exception {
    /**
     * Constructor for this exception.
     * @param message the bot response when this exception occurs
     */
    public EmptyNameException(String message) {
        super(message);
    }
}
