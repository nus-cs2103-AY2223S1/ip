package ted.exception;

/**
 * Thrown when we cannot handle user's input
 */
public class TedException extends Exception {
    public TedException(String message) {
        super(message);
    }
}
