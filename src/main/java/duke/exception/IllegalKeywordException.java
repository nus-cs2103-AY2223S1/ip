package duke.exception;

/**
 * IllegalKeywordException is an exception relating to invalid keyword input.
 *
 * @author totsukatomofumi
 */
public class IllegalKeywordException extends IllegalArgumentException {
    /**
     * Constructs an IllegalKeywordException with the specified detail message.
     * @param s the detail message.
     */
    public IllegalKeywordException(String s) {
        super(s);
    }
}
