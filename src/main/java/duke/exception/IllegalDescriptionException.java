package duke.exception;

/**
 * IllegalDescriptionException is an exception relating to invalid description input.
 *
 * @author totsukatomofumi
 */
public class IllegalDescriptionException extends IllegalArgumentException {
    /**
     * Constructs an IllegalDescriptionException with the specified detail message.
     * @param s the detail message.
     */
    public IllegalDescriptionException(String s) {
        super(s);
    }
}
