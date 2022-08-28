package uwu.exception;

/**
 * Signals an error caused by an empty task description.
 */
public class EmptyInputException extends UwuException {
    /**
     * Constructor for an EmptyInputException object.
     *
     * @param message The exception message to be displayed.
     */
    public EmptyInputException(String message) {
        super(message);
    }
}
