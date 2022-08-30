package duke.exception;

/**
 * Throws an exception when task is already marked.
 */
public class TaskMarkException extends DukeException {
    /**
     * Throws an error message indicating that the task is already marked.
     */
    public TaskMarkException() {
        super("Task is already marked.");
    }
}
