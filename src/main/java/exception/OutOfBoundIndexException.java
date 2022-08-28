package exception;

/**
 * This exception is thrown when user is trying to access a task with an out of bound index.
 */
public class OutOfBoundIndexException extends DukeException {

    /**
     * Creates a MissingTaskException.
     */
    public OutOfBoundIndexException() {
        super("☹ OOPS!!! The index you used is out of bound!");
    }
}
