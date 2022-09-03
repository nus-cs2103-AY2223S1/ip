package duke.exception;

/**
 * Creates an exception that is thrown when the user inputs a non-numerical index or
 * an invalid index.
 */
public class InvalidIndexException extends DukeException {
    private static final String ERR_STARTING_MESSAGE = "Unfortunate, ";
    public InvalidIndexException(String errorMsg) {
        super(ERR_STARTING_MESSAGE + errorMsg);
    }
}
