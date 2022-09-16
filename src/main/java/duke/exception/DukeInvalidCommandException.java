package duke.exception;

/**
 * Thrown when Duke encounters an invalid command.
 *
 * @author Lim Ai Lin
 */
public class DukeInvalidCommandException extends DukeException {

    /**
     * Creates a DukeInvalidCommandException.
     */
    public DukeInvalidCommandException() {
        super("RAWR! I don't know what that means.");
    }
}
