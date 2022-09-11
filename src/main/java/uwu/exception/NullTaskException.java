package uwu.exception;

/**
 * Signals an error when the task does not exist.
 */
public class NullTaskException extends UwuException {
    /**
     * Constructs a NullTaskException object.
     *
     * @param message The exception message to be displayed.
     */
    public NullTaskException(String message) {
        super(message);
    }
}
