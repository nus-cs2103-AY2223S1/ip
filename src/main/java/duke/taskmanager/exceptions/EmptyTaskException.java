package duke.taskmanager.exceptions;

public class EmptyTaskException extends Exception {
    /**
     * Exception that handles tasks with empty task names.
     */
    public EmptyTaskException() {
        super("You cannot have an empty Task!\n");
    }
}