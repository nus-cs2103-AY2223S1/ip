package duke.exception;

/**
 * Represents the exception which occurs when the user inputs an unrecognised command.
 *
 * @author njxue
 * @version v0.1
 */
public class UnknownCommandException extends DukeException {
    /**
     * Returns an UnknownCommandException.
     *
     * @param invalidCommand Invalid command provided by the user.
     */
    public UnknownCommandException(String invalidCommand) {
        super(invalidCommand + " is not a valid command!");
    }
}
