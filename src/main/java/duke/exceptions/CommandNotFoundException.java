package duke.exceptions;

/**
 * Representation of an exception where user input does not match valid commands.
 */
public class CommandNotFoundException extends DukeException {
    /**
     * Informs user that the command that he input does not exists.
     * @param message
     */
    public CommandNotFoundException(String message) {
        super("THIS COMMAND, "
                + message
                + ", DOES NOT EXISTS");
    }

}
