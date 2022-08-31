package duke.command;

import duke.Response;
import duke.exception.DukeException;

/**
 * Represent a command to be executed.
 */
public abstract class Command {
    /**
     * Performs an action due to the command.
     * @return The Response to be displayed upon the execution of the command.
     * @throws DukeException If the command cannot be executed to completion.
     */
    public abstract Response execute() throws DukeException;
}
