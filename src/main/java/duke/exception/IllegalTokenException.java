package duke.exception;

/**
 * IllegalTokenException is an exception relating to invalid or missing tokens in input.
 *
 * @author totsukatomofumi
 */
public class IllegalTokenException extends IllegalArgumentException {
    /**
     * Constructs an IllegalTokenException with the specified detail message.
     * @param message the detail message.
     */
    public IllegalTokenException(String message) {
        super(message);
    }
}
