package duke.exceptions;

/**
 * Represents an exception due to an unknown command.
 */
public class UnknownCommandException extends DukeException {
    private static final String message = "Unknown command!";

    /**
     * Constructs an unknown command exception.
     */
    public UnknownCommandException() {
        super(message);
    }
}
