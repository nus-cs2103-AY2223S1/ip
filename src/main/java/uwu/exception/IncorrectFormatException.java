package uwu.exception;

/**
 * Signals an error caused by the user command missing the keyword.
 */
public class IncorrectFormatException extends UwuException {
    /**
     * Constructor for an IncorrectFormatException object.
     *
     * @param message The exception message to be displayed.
     */
    public IncorrectFormatException(String message) {
        super(message);
    }
}
