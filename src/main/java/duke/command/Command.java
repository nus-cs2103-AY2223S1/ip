package duke.command;

import duke.CustomMessageException;

/**
 * Abstract class to represent a {@code Command}
 */
public abstract class Command {
    /**
     * Executes the Command
     * @return The output to display to the user
     * @throws CustomMessageException Exception to be thrown if the user passes invalid commands
     */
    public abstract String execute() throws CustomMessageException;
}
