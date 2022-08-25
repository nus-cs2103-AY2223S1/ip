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
    public void execute() throws DukeException {
        throw new DukeException("Unknown command. Please try again.");
    }
}
