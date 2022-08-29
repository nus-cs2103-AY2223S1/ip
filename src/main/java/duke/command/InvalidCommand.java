package duke.command;

import duke.DukeException;

/**
 * Represents an invalid, unrecognised command given by the user.
 */
public class InvalidCommand extends Command {
    /**
     * {@inheritDoc}
     */
    @Override
    public String execute() throws DukeException {
        throw new DukeException("Unknown command, please try again! Type \"help\" for a list of commands.");
    }
}
