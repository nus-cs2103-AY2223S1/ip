package duke.exception;

/**
 * IllegalTaskException is an exception relating to invalid task input.
 *
 * @author totsukatomofumi
 */
public class IllegalTaskException extends IllegalArgumentException {
    /**
     * Constructs an IllegalTaskException with the specified detail message.
     * @param s the detail message.
     */
    public IllegalTaskException(String s) {
        super(s);
    }
}
