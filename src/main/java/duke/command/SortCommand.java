package duke.command;

import duke.common.DukeException;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Command to sort all tasks by status then by title
 *
 * @author Pontakorn Prasertsuk
 */
public class SortCommand extends Command {

    /**
     * Executes the ReminderCommand
     *
     * @param taskList the task list to be sorted
     * @param ui the user interface to be used
     * @param storage the storage to be used
     * @return output to be shown
     * @throws DukeException if an error occurs
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        ui.showOutput(taskList.sort().toString());
        storage.save(taskList.getTaskList());

        return taskList.sort().toString();
    }

    /**
     * Returns false as this is not the exit command
     *
     * @return false
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
