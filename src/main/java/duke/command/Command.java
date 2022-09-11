package duke.command;

import duke.common.DukeException;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Represents a command made by the user to Duke.
 *
 * @author Rama Aryasuta Pangestu
 */
public abstract class Command {
    /**
     * Executes the command made by the user.
     *
     * @param ui       the user interface
     * @param storage  the storage dealing with loading and saving tasks in the save file
     * @param taskList the task list
     * @throws DukeException if an error occurs when executing the command
     */
    public abstract void execute(Ui ui, Storage storage, TaskList taskList) throws DukeException;

    /**
     * Returns a boolean denoting whether Duke should exit after executing this command.
     *
     * @return true if and only if this is an exit command
     */
    public abstract boolean isExit();
}
