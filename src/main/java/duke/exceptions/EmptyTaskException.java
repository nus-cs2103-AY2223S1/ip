package duke.exceptions;

/**
 * Represents an EmptyTaskException which is thrown when
 * a task description is missing.
 */
public class EmptyTaskException extends Exception {

    /**
     * Constructs an EmptyTaskException with Duke Aemon's standard message.
     */
    public EmptyTaskException() {
        super("You present me with no task. Speak its burden, or seek guidance with 'help'.");
    }
}
