package chad.exception;

/**
 * Custom exception
 */
public class ChadException extends Exception {
    /**
     * Adds additional message to messaged parsed
     * @param message Error message
     */
    public ChadException(String message) {
        super("☹ OOPS!!! " + message);
    }
}
