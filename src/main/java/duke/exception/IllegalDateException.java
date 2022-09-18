package duke.exception;

/**
 * IllegalDateException is an exception relating to invalid date input.
 *
 * @author totsukatomofumi
 */
public class IllegalDateException extends IllegalArgumentException {
    /**
     * Constructs an IllegalDateException with the specified detail message.
     * @param message the detail message.
     */
    public IllegalDateException(String message) {
        super(message);
    }
}
