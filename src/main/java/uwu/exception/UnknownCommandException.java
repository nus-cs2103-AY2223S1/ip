package uwu.exception;

/**
 * Signals an error when the command entered is unknown.
 */
public class UnknownCommandException extends UwuException {
    /**
     * Constructor for an UnknownCommandException object.
     *
     * @param message The exception message to be displayed.
     */
    public UnknownCommandException(String message) {
        super(message);
    }
}
