package jean.exception;

/**
 * Represents a generic error specific to Jean.
 */
public class JeanException extends Exception {

    /**
     * Creates a JeanException.
     *
     * @param message The error message.
     */
    public JeanException(String message) {
        super(message);
    }
}
