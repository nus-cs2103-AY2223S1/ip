package duke.exception;

/**
 * Creates an exception that is thrown when the user inputs an invalid command.
 */
public class InvalidInputException extends DukeException {
    public InvalidInputException() {
        super("This means nothing to me");
    }
}
