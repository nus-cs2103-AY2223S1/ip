package duke.exceptions;

/**
 * Representation of an exception where user input does not match valid commands.
 */
public class CommandNotFoundException extends DukeException {

    public CommandNotFoundException(String message) {
        super(message + " DOES NOT EXISTS");
    }

}
