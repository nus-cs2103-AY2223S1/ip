package duke.command;

import duke.Response;
import duke.exception.DukeException;

/**
 * Command represents a command to be executed.
 */
public abstract class Command {

    /**
     * Executes an action in response to the command
     *
     * @return The Response to be displayed.
     * @throws DukeException If the action cannot be executed.
     */
    public abstract Response execute() throws DukeException;
}
