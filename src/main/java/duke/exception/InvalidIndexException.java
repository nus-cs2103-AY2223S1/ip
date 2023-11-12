package duke.exception;

/**
 * Representation for invalid index exception.
 */
public class InvalidIndexException extends DukeException {
    private static final String message = "OOPS!!! This item doesn't exist :-(";

    /**
     * Constructor for InvalidIndexException.
     */
    public InvalidIndexException() {
        super(message);
    }
}
