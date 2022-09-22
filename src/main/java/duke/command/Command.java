package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.exception.DukeException;

/**
 * An abstract class that encapsulates the command.
 */
public abstract class Command {
    /**
     * Handles the execution behaviour of the associated command.
     *
     * @param tasks The current list of tasks.
     * @param storage The storage of data.
     * @throws DukeException If there is an error that arise from the
     *                       associated behaviour of the command.
     */
    public abstract String execute(TaskList tasks, Storage storage) throws DukeException;

    /**
     * Returns the command type.
     *
     * @return The command type.
     */
    public abstract String getCommand();

    /**
     * Returns the string representation of the command.
     *
     * @return The string representation of the command.
     */
    @Override
    public abstract String toString();
}
