package duke.exceptions;

/**
 * Represents an EmptyCommandException which is thrown when
 * no command is entered by the user.
 */
public class EmptyCommandException extends Exception {

    /**
     * Constructs an EmptyCommandException with Duke Aemon's standard message.
     */
    public EmptyCommandException() {
        super("You speak nothing, and yet expect an answer. Consult 'help' if guidance is needed.");
    }
}
