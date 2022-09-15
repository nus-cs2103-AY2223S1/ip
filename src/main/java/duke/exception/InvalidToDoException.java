package duke.exception;

/**
 * Representation for invalid fields of todo task exception.
 */
public class InvalidToDoException extends DukeException {
    private static final String message = "Hmm, description cannot be empty.";

    /**
     * Constructor for InvalidToDoException.
     */
    public InvalidToDoException() {
        super(message);
    }
}
