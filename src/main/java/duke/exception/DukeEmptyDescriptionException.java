package duke.exception;

/**
 * Thrown when task or note description is not given.
 *
 * @author Lim Ai Lin
 */
public class DukeEmptyDescriptionException extends DukeException {

    /**
     * Creates a DukeEmptyDescriptionException.
     */
    public DukeEmptyDescriptionException() {
        super("RAWR! Missing description.");
    }
}
