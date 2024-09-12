package tako;

/**
 * Represents all exceptions related to Tako.
 */
public class TakoException extends Exception {
    /**
     * Constructor for TakoException with custom error message.
     *
     * @param message Error message to display.
     */
    public TakoException(String message) {
        super(message);
    }
}
