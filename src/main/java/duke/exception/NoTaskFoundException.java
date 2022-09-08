package duke.exception;

/**
 * Throws an exception TaskList is empty.
 */
public class NoTaskFoundException extends DukeException {
    public static final String EXCEPTION_DESCRIPTION = "No task found!";
    /**
     * Throws an error message when TaskList is empty.
     */
    public NoTaskFoundException() {
        super(EXCEPTION_DESCRIPTION);
    }
}
