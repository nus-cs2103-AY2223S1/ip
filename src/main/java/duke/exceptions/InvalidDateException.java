package duke.exceptions;

/**
 * Represents an exception due to invalid date.
 */
public class InvalidDateException extends DukeException {
    private static final String message = "Invalid date!";

    /**
     * Constructs an invalid date exception.
     */
    public InvalidDateException() {
        super(message);
    }
}
