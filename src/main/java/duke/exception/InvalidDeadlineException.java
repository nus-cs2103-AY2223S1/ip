package duke.exception;

/**
 * Representation for invalid deadline command exception.
 */
public class InvalidDeadlineException extends DukeException {
    private static final String message = "Hmm, input doesn't match the 'deadline' format."
            + "\ne.g deadline return book /by 2022-10-10";

    /**
     * Constructor with default error message.
     */
    public InvalidDeadlineException() {
        super(message);
    }

    /**
     * Constructor to set custom error message.
     * @param mess Error message.
     */
    public InvalidDeadlineException(String mess) {
        super(mess);
    }
}
