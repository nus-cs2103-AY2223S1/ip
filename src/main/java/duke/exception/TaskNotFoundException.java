package duke.exception;

/**
 * Customised exception for unrecognised task commands.
 */
public class TaskNotFoundException extends Exception{

    /**
     * Creates a TaskNotFoundException.
     * @param message Input that was not recognised.
     */
    public TaskNotFoundException(String message) {
        super(message);
    }
}
