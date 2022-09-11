package duke.exception;

/**
 * Empty description exception.
 */
public class EmptyDateException extends DukeException {

    /**
     * Constructor for empty description exception.
     */
    public EmptyDateException() {
        super("☹ OOPS!!! The date cannot be empty.");
    }
}
