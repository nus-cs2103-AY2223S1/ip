package duke.exceptions;

/**
 * Represents an EmptyCommandException class which is inherited from the Exception class and
 * occurs when the user enters an empty command
 */
public class EmptyCommandException extends Exception {

    /**
     * Constructs an EmptyCommandException with standard message
     */
    public EmptyCommandException() {
        super("Duke Aemon detected no command from the user. Use the `help` command to see all commands supported.");
    }
}
