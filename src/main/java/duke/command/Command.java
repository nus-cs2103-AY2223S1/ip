package duke.command;

import duke.common.DukeException;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Command input by user
 *
 * @author Pontakorn Prasertsuk
 */
public abstract class Command {

    /**
     * Executes the Command
     *
     * @param taskList the task list to be mutated
     * @param ui the user interface to be used
     * @param storage the storage to be used
     * @return output to be shown
     * @throws DukeException if an error occurs
     */
    public abstract String execute(TaskList taskList, Ui ui, Storage storage) throws DukeException;

    /**
     * Returns false as this is not the exit command
     *
     * @return true if exiting the application after executing this command
     */
    public abstract boolean isExit();
}
