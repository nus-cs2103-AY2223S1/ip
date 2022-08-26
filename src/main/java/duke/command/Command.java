package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

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
     * @param ui User interface for Duke.
     * @param storage A Storage object to handle saving and loading of tasks.
     * @throws DukeException  If an error occurs trying to execute the command.
     */
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException;
}
