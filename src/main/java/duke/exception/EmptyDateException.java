package duke.exception;

/**
 * Empty description exception.
 */
public class EmptyDateException extends DukeException {

    /**
     * Constructor for empty description exception.
     */
    public EmptyDateException() {
        super("\nDate cannot be empty, please enter your date behind the forward slash /");
    }
}
