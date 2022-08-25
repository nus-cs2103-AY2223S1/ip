package duke.command;

import duke.exception.DukeException;

/**
 * Represent a command to be executed.
 */
public abstract class Command {
    /**
     * Performs an action due to the command.
     * @return The message to be displayed upon the execution of the command.
     * @throws DukeException If the command cannot be executed to completion.
     */
    public abstract String execute() throws DukeException;
}
