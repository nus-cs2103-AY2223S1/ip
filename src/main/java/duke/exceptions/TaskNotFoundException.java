package duke.exceptions;

/**
 * The type Task not found exception.
 */
public class TaskNotFoundException extends DukeException {

    /**
     * Instantiates a new Task not found exception.
     */
    public TaskNotFoundException() {
        super("Sorry this task does not exist");
    }
}
