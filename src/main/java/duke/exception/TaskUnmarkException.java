package duke.exception;

public class TaskUnmarkException extends DukeException {
    public TaskUnmarkException() {
        super("Task has not been marked!");
    }
}
