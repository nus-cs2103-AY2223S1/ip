package duke.exception;

/**
 * An exception thrown when a task index is out of bounds
 * (i.e. the index is not within the current list of tasks).
 */
public class TaskIndexOutOfBoundsException extends DukeException {
    public TaskIndexOutOfBoundsException(int taskIndex) {
        super("Task index " + taskIndex + " out of bounds");
    }
}
