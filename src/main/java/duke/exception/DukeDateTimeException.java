package duke.exception;

/**
 * A DukeDateTimeException is thrown when a date String inputted by the user is invalid.
 */
public class DukeDateTimeException extends DukeException {
    public DukeDateTimeException() {
        super("Please input a date in the following format: dd/MM/yyyy HH:mm");
    }
}
