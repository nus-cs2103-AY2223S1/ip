package duke.exception;

/**
 * The InvalidIndexException class represents a DukeException that is thrown when
 * the user tries to mark, unmark, or delete an invalid index.
 *
 * @author Edric Yeo
 */
public class InvalidIndexException extends DukeException {
    /**
     * Creates an EmptyDateException instance.
     */
    public InvalidIndexException() {
        super("Invalid index!");
    }
}
