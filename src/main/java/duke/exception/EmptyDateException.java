package duke.exception;

/**
 * Empty description exception.
 */
public class EmptyDateException extends DukeException {

    /**
     * Constructor for empty description exception.
     */
    public EmptyDateException() {
        super("\nThe date cannot be empty.");
    }
}
