package duke.exception;

public class TaskNotFoundDukeException extends DukeException {
    public TaskNotFoundDukeException() {
        super("I couldn't find the task you were looking for...");
    }

}
