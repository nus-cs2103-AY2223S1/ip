package duke.exceptions;

/**
 * Represents an exception due to an invalid index.
 */
public class InvalidIndexException extends DukeException {
    private static final String message = "Invalid index!";

    /**
     * Constructor for an invalid index exception.
     */
    public InvalidIndexException() {
        super(message);
    }
}
