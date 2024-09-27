package duke.exception;

/**
 * Creates an exception that is thrown when the user inputs an invalid command.
 */
public class InvalidInputException extends DukeException {
    private static final String ERR_MESSAGE = "This means nothing to me";
    public InvalidInputException() {
        super(ERR_MESSAGE);
    }
}
