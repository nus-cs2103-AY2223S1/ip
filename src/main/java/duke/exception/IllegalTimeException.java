package duke.exception;

/**
 * IllegalTimeException is an exception relating to invalid time input.
 *
 * @author totsukatomofumi
 */
public class IllegalTimeException extends IllegalArgumentException {
    /**
     * Constructs an IllegalTimeException with the specified detail message.
     * @param s the detail message.
     */
    public IllegalTimeException(String s) {
        super(s);
    }
}
