package duke.exception;

/**
 * Represents the exception that will be thrown when the user inputs an invalid priority for priority command.
 */
public class InvalidPriorityException extends DukeException {
    private static final String ERR_MESSAGE = "Invalid priority status entered";
    public InvalidPriorityException() {
        super(ERR_MESSAGE);
    }
}
