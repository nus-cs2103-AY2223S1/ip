package duke.exceptions;

/**
 * Representation of an exception where Duke cannot sort taskList.
 */
public class CannotSortException extends DukeException {
    public CannotSortException(String message) {
        super(message);
    }
}
