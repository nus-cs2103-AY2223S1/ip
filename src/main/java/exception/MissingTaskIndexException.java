package exception;

/**
 * This exception is thrown when the user fails to give an index for the task they want to manage.
 */
public class MissingTaskIndexException extends DukeException {

    /**
     * Creates a MissingTaskIndexException
     */
    public MissingTaskIndexException() {
        super("☹ OOPS!!! Missing task index!");
    }
}
