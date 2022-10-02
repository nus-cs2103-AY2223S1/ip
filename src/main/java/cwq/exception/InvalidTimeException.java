package cwq.exception;

/**
 * InvalidTimeException is thrown when the input time format is wrong
 */
public class InvalidTimeException extends DukeExceptions {
    /**
     * Constructor for InvalidTimeException.
     * @param msg exception message
     */
    public InvalidTimeException(String msg) {
        super(msg);
    }
}
