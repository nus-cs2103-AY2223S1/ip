package duke.exception;

/**
 * Empty description exception.
 */
public class EmptyDescriptionException extends DukeException {

    /**
     * Constructor for empty description exception.
     */
    public EmptyDescriptionException() {
        super("\nThe description cannot be empty.");
    }
}
