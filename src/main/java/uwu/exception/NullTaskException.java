package uwu.exception;

/**
 * Signals an error when the task does not exist.
 */
public class NullTaskException extends UwuException {
    /**
     * Constructor for a NullTaskException object.
     *
     * @param message The exception message to be displayed.
     */
    public NullTaskException(String message) {
        super(message);
    }
}
