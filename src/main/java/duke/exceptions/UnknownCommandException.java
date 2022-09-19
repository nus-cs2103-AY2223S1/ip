package duke.exceptions;

/**
 * Thrown when user put an invalid command.
 */
public class UnknownCommandException extends DukeException {
    private static final String DESCRIPTION = "What's that?";

    public UnknownCommandException() {
        super(DESCRIPTION);
    }
}
