package duke.exception;

/**
 * Creates an exception that is thrown when the user inputs an incomplete command.
 */
public class MissingDescriptionException extends DukeException {
    public MissingDescriptionException(String str) {
        super("Unfortunate. The description of a " + str + " cannot be empty");
    }
}
