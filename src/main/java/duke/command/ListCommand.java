package duke.command;

import duke.common.DukeException;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Command to list all tasks in the task list
 *
 * @author Pontakorn Prasertsuk
 */
public class ListCommand extends Command {

    /**
     * Executes the ListCommand
     *
     * @param taskList the task list to be shown
     * @param ui       the user interface to be used
     * @param storage  not being used
     * @return output to be shown
     * @throws DukeException if an error occurs
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        ui.showOutput(taskList.toString());

        return taskList.toString();
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
