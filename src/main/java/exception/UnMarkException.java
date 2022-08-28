package exception;

/**
 * This exception is thrown when the user is trying to unmark a task that has already been unmarked.
 */
public class UnMarkException extends DukeException {

    /**
     * Constructor for UnMarkException.
     */
    public UnMarkException() {
        super("☹ OOPS!!! The task you want to unmark is already unmarked.");
    }
}
