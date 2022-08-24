package duke.command;

import duke.exception.DukeException;
/**
 * Command represents a command to be executed.
 */
public abstract class Command {
    /**
     * Performs an action in response to the command/
     * @return The String message to be displayed.
     * @throws DukeException If the action cannot be executed.
     */
    public abstract String action() throws DukeException;
}
