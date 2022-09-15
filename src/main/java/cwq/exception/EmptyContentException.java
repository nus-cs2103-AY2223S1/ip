package cwq.exception;

/**
 * EmptyContentException is thrown when the task content is empty
 */
public class EmptyContentException extends DukeExceptions {
    /**
     * Constructor for EmptyContentException.
     * @param msg exception message
     */
    public EmptyContentException(String msg) {
        super(msg);
    }
}
