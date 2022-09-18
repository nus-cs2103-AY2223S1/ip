package duke.exception;

/**
 * Represents the exception which occurs when the user inputs a correct command, but it is incorrectly formatted.
 *
 * @author njxue
 * @version v0.1
 */
public class InvalidCommandFormatException extends DukeException {
    /**
     * Returns an InvalidCommandFormatException.
     *
     * @param format Correct format for the command.
     */
    public InvalidCommandFormatException(String format) {
        super(String.format("Invalid command format.\nUsage: %s", format));
    }
}
