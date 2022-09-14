package duke.exception;

/**
 * Representation for invalid deadline command exception.
 */
public class InvalidDeadlineException extends DukeException {
    private static final String message = "OOPS!!! The description or date of a deadline cannot be empty.";

    /**
     * Constructor with default error message.
     */
    public InvalidDeadlineException() {
        super(message);
    }

    /**
     * Constructor with custom error message.
     * @param mess Error message.
     */
    public InvalidDeadlineException(String mess) {
        super(mess);
    }
}
