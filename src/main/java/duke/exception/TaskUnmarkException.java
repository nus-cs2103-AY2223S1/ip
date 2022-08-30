package duke.exception;

/**
 * Throws an exception when the task is already unmark.
 */
public class TaskUnmarkException extends DukeException {
    /**
     * Throws an error message when the task is already unmark.
     */
    public TaskUnmarkException() {
        super("Task has not been marked!");
    }
}
