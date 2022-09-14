package exceptions;

/**
 * Exception when the user tries to access an empty task list.
 */
public class NoTasksException extends Exception {
    /**
     * Constructor for this exception.
     */
    public NoTasksException() {
        super("You can't do that! There are no tasks added yet...");
    }
}
