package duke.exceptions;

/**
 * Represents an exception due to an unknown command.
 */
public class UnknownCommandException extends DukeException {
    private static final String MESSAGE = "Whoops, I didn't quite understand that!\n"
            + "Try pressing <UP> arrow key and fixing the command!";

    /**
     * Constructs an unknown command exception.
     */
    public UnknownCommandException() {
        super(MESSAGE);
    }
}
