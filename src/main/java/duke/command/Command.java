package duke.command;

import duke.exception.DukeException;
import duke.manager.Storage;
import duke.manager.TaskList;

/**
 * Represents the user's command to be executed. E.g. delete, add, mark task
 */
public abstract class Command {
    /**
     * Returns whether the Duke program should exit.
     *
     * @return Whether the Duke program should exit.
     **/
    public boolean isExit() {
        return false;
    }

    /**
     * Executes the given command.
     *
     * @param tasks List of current tasks.
     * @param storage A Storage object to handle saving and loading of tasks.
     * @throws DukeException  If an error occurs trying to execute the command.
     */
    public abstract String execute(TaskList tasks, Storage storage) throws DukeException;
}
