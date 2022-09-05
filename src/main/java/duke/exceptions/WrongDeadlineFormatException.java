package duke.exceptions;

/**
 * Represents an exception due to a wrong deadline format.
 */
public class WrongDeadlineFormatException extends WrongFormatException {
    private static final String message =
            "Wrong format for Deadline!\\nShould be 'deadline <description> /by YYYY-MM-DD HHmm'.";

    /**
     * Constructor for an invalid index exception.
     */
    public WrongDeadlineFormatException() {
        super(message);
    }
}
