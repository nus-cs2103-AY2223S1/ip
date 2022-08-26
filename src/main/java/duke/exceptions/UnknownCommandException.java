package duke.exceptions;

/**
 * Thrown when user put an invalid command.
 */
public class UnknownCommandException extends DukeException {
    private static final String DESCRIPTION = "I'm sorry, but I don't know what that means :-(";

    public UnknownCommandException() {
        super(DESCRIPTION);
    }
}
