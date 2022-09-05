package duke.exceptions;

/**
 * Represents an exception due to an unknown command.
 */
public class UnknownCommandException extends DukeException {
    private static final String message = "Unknown command!";

    /**
     * Constructor for an invalid index exception.
     */
    public UnknownCommandException() {
        super(message);
    }
}
