package duke.exception;

public class TaskIndexOutOfBoundsException extends DukeException {
    public TaskIndexOutOfBoundsException(int taskIndex) {
        super("Task index " + taskIndex + " out of bounds");
    }
}
