package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * The abstract class which recognizes the user's command.
 *
 * @author Bryan Ng Zi Hao
 */
public abstract class Command {
    /**
     * Executes the command.
     *
     * @param ui The interactions with user being used.
     * @param storage The storage which the data is being stored.
     * @param taskList The list of tasks to be updated in the storage.
     * @throws DukeException There is an error in execution.
     */
    public abstract void execute(Ui ui, Storage storage, TaskList taskList) throws DukeException;

    /**
     * Checks if this command will result in the bot to stop running.
     *
     * @return a boolean value.
     */
    public abstract boolean isRunning();
}
