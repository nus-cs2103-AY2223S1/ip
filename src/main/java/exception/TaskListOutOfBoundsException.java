package exception;

/**
 * When the user specifies a positional argument for tasklist that is out of bounds for tasklist of that size
 */
public class TaskListOutOfBoundsException extends DukeException {
    
    public TaskListOutOfBoundsException(String message) {
        super(message);
    }
}