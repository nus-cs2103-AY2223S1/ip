package cwq.exception;

/**
 * NoSuchTaskException is thrown when the target task doesn't exist
 */
public class NoSuchTaskException extends DukeExceptions {
    /**
     * Constructor for NoSuchTaskException.
     * @param msg exception message
     */
    public NoSuchTaskException(String msg) {
        super(msg);
    }
}
