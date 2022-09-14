package duke.exception;

/**
 * Representation for invalid fields of todo task exception.
 */
public class InvalidToDoException extends DukeException {
    private static final String message = "OOPS!!! The description of a todo cannot be empty.";

    /**
     * Constructor for InvalidToDoException.
     */
    public InvalidToDoException() {
        super(message);
    }
}
