package uwu.exception;

/**
 * Signals an error when the date entered by the user invalid or in
 * the incorrect format.
 */
public class InvalidDateException extends UwuException {
    /**
     * Constructor for an InvalidDateException object.
     *
     * @param message The exception message to be displayed.
     */
    public InvalidDateException(String message) {
        super(message);
    }
}
