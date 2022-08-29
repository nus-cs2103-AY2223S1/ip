package duke.command;

import duke.DukeException;

/**
 * Represents a Command with an execute function.
 */
public abstract class Command {
    /**
     * Executes the purpose of the command and returns the message from Duke.
     *
     * @return The String message of Duke's response.
     * @throws DukeException if an error occurs when executing the command.
     */
    public abstract String execute() throws DukeException;
}
