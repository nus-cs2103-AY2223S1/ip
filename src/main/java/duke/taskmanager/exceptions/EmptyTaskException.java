package duke.taskmanager.exceptions;

/**
 * Exception that is thrown when there is an empty task.
 */
public class EmptyTaskException extends Exception {
    /**
     * Exception that handles tasks with empty task names.
     */
    public EmptyTaskException() {
        super("You cannot have an empty Task!\n");
    }
}
