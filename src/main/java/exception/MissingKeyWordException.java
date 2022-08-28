package exception;

/**
 * This exception is thrown when user forgets to input a keyword to find tasks with.
 */
public class MissingKeyWordException extends DukeException {

    /**
     * Creates a MissingKeyWordException.
     */
    public MissingKeyWordException() {
        super("☹ OOPS!!! The keyword is missing!");
    }
}
