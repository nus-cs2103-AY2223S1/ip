package duke.command;

import duke.DukeException;

/**
 * Represents a Command with an execute function.
 */
public abstract class Command {
    /**
     * Executes the purpose of the command.
     *
     * @throws DukeException if an error occurs when executing the command.
     */
    public abstract void execute() throws DukeException;
}
