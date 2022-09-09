package raiden.command;

import raiden.RaidenException;

/**
 * Represents an invalid, unrecognised command given by the user.
 */
public class InvalidCommand extends Command {
    /**
     * {@inheritDoc}
     */
    @Override
    public String execute() throws RaidenException {
        throw new RaidenException("Unknown command, please try again. \nType \"help\" for a list of commands.");
    }
}
