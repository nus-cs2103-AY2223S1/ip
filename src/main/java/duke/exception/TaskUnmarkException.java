package duke.exception;

/**
 * Throws an exception when the task is already unmark.
 */
public class TaskUnmarkException extends DukeException {
    public static final String EXCEPTION_DESCRIPTION = "Task has not been marked!";
    /**
     * Throws an error message when the task is already unmark.
     */
    public TaskUnmarkException() {
        super(EXCEPTION_DESCRIPTION);
    }
}
