package duke.exception;

/**
 * Creates an exception that is thrown when the user inputs an incomplete command.
 */
public class MissingDescriptionException extends DukeException {
    private static final String ERR_STARTING_MESSAGE = "Unfortunate, the description of a ";
    private static final String ERR_ENDING_MESSAGE = " cannot be empty";
    public MissingDescriptionException(String command) {
        super(ERR_STARTING_MESSAGE + command + ERR_ENDING_MESSAGE);
    }

    public MissingDescriptionException(String command, String endingMessage) {
        super(ERR_STARTING_MESSAGE + command + endingMessage);
    }
}
