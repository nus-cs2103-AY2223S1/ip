package duke.exception;

/**
 * Creates an exception that is thrown when the user inputs an invalid date for the due command.
 */
public class InvalidDateException extends DukeException {
    public InvalidDateException(String msg) {
        super(msg);
    }
}
