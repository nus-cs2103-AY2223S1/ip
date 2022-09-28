package duke.exceptions;

/**
 * Exception that occurs when a Task needs to be accessed from the list
 */
public class TaskNotFoundException extends Exception{
    public TaskNotFoundException(String message) {
        super(message);
    }
}
