package raiden.command;

import raiden.RaidenException;

/**
 * Represents a Command with an execute function.
 */
public abstract class Command {
    /**
     * Executes the purpose of the command and returns the message from Raiden.
     *
     * @return The String message of Raiden's response.
     * @throws RaidenException if an error occurs when executing the command.
     */
    public abstract String execute() throws RaidenException;
}
