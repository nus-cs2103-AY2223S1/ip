package duke.exception;

/**
 * Throws an exception when task is already marked.
 */
public class TaskMarkException extends DukeException {
    public static String EXCEPTION_DESCRIPTION = "Task is already marked.";
    /**
     * Throws an error message indicating that the task is already marked.
     */
    public TaskMarkException() {
        super(EXCEPTION_DESCRIPTION);
    }
}
