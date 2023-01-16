package carbon.error;

/**
 * Main abstract class for all exceptions related to Carbon.
 */
public abstract class CarbonException extends RuntimeException {
    public CarbonException(String message) {
        super(message);
    }

    @Override
    public String toString() {
        return "Hold up, something's off.";
    }
}
