package duke.common;

/**
 * TaskNotFoundException class
 */
public class TaskNotFoundException extends RuntimeException {
    public TaskNotFoundException(String message) {
        super(message);
    }
}
