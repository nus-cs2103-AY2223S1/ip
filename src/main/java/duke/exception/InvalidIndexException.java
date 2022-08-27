package duke.exception;

/**
 * Creates an exception that is thrown when the user inputs a non-numerical index or
 * an invalid index.
 */
public class InvalidIndexException extends DukeException {
    public InvalidIndexException(String errorMsg) {
        super("Unfortunate, " + errorMsg);
    }
}
