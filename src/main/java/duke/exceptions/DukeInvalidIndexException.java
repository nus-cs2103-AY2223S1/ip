package duke.exceptions;

/**
 * This class encapsulates a DukeException caused by an invalid task index.
 */
public class DukeInvalidIndexException extends DukeException {

    /**
     * Constructs a DukeInvalidIndexException exception.
     */
    public DukeInvalidIndexException() {
        super("Exception: Invalid task index.");
    }
}
