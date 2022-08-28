package exception;

/**
 * This exception is thrown when the user is trying to mark a task that has already been marked.
 */
public class MarkException extends DukeException {

    /**
     * Constructor for MarkException.
     */
    public MarkException() {
        super("☹ OOPS!!! The task you want to mark is already marked.");
    }
}
