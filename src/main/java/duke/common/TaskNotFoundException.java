package duke.common;

public class TaskNotFoundException extends RuntimeException{
    public TaskNotFoundException(String message) {
        super(message);
    }
}
