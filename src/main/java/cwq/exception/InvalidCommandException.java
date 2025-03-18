package cwq.exception;

/**
 * InvalidCommandException is thrown when the input command is invalid.
 */
public class InvalidCommandException extends DukeExceptions {
    /**
     * Constructor for InvalidCommandException.
     * @param msg exception message
     */
    public InvalidCommandException(String msg) {
        super(msg);
    }
}
