package duke.exceptions;

/**
 * Represents an EmptyTaskException class which is inherited from the Exception class and
 * occurs when the user enters an empty task argument
 */
public class EmptyTaskException extends Exception {

    /**
     * Constructs an EmptyTaskException with standard message
     */
    public EmptyTaskException() {
        super("Duke Aemon detected no task from the user. Use the `help` command to see all commands supported.");
    }
}
