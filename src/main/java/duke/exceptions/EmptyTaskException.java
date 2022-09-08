package duke.exceptions;

/**
 * Custom exception thrown when a task with an empty description is generated
 */
public class EmptyTaskException extends Exception {
    public EmptyTaskException(String message) {
        super(message);
    }
}
