package exceptions;

public class TaskNotFoundException extends DukeException {
    public TaskNotFoundException() {
        super("Sorry this task does not exist");
    }
}
