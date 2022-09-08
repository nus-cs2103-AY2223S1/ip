package duke.exception;

/**
 * Representation for invalid event command exception.
 */
public class InvalidEventException extends DukeException {
    private static final String message = "☹ OOPS!!! The description or date of an event cannot be empty.";

    /**
     * Constructor for InvalidEventException.
     */
    public InvalidEventException() {
        super(message);
    }
}
