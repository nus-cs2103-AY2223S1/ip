package exception;

/**
 * Represents exceptions specific to Luna.
 *
 * @author fannyjian
 */
public class LunaException extends Exception {

    /**
     * Creates a new exception with the error message.
     *
     * @param message Error message.
     */
    public LunaException(String message) {
        super(message);
    }
}