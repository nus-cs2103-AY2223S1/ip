package duke.exception;

/**
 * Thrown to indicate that the input command is not recognised as a valid command.
 *
 * @author Justin Peng
 */
public class DukeInvalidCommandException extends DukeException {
    /**
     * Constructs a {@code DukeInvalidCommandException} with the default message.
     */
    public DukeInvalidCommandException() {
        super("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
    }
}
