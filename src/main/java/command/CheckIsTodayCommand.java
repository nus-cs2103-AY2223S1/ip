package command;

import exceptions.DukeException;
import storage.Storage;
import tasklist.TaskList;
import ui.UI;

/**
 * Command that runs on dated tasks like
 * Deadline or Event.
 * Returns true if the task is due today.
 */
public class CheckIsTodayCommand extends Command {
    private int index;

    public CheckIsTodayCommand(int index) {
        this.index = index;
    }
    /**
     * Checks if task at index n in taskList
     * is due/happening today.
     *
     * @param taskList taskList provides access to Task.
     * @param ui ui provides user command.
     * @param storage Not needed.
     * @throws DukeException when task is not found in taskLst.
     */
    @Override
    public void execute(TaskList taskList, UI ui, Storage storage) throws DukeException {
        if (isDueToday(index, taskList)) {
            ui.showMessage("Yes due today");
        } else {
            ui.showMessage("No not due today");
        }
    }

    private boolean isDueToday(int index, TaskList taskList) {
        return taskList.checkIsToday(index);
    }
}
