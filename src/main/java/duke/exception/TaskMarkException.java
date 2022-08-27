package duke.exception;

public class TaskMarkException extends DukeException{
    public TaskMarkException() {
        super("Task is already marked.");
    }
}
