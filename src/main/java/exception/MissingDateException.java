package exception;

/**
 * This exception is thrown when the user forgets to input the date of the task.
 */
public class MissingDateException extends DukeException {

    /**
     * Creates a MissingDateException.
     */
    public MissingDateException() {
        super("☹ OOPS!!! The date of the task is missing!");
    }
}
