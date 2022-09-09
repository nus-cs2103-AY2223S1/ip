package duke.exception;

/**
 * Empty command exception for Duke application.
 *
 * @author Farrel Dwireswara Salim
 */
public class EmptyCommandException extends DukeException {
    /**
     * Constructs a new default EmptyCommandException.
     */
    public EmptyCommandException() {
        super("The command cannot be empty.");
    }
}
