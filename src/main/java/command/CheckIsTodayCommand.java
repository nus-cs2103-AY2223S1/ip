package command;

import exceptions.DukeException;
import storage.Storage;
import tasklist.TaskList;
import ui.UI;
import utility.Parser;

/**
 * Command that runs on dated tasks like
 * Deadline or Event.
 * Returns true if the task is due today.
 */
public class CheckIsTodayCommand extends Command {
    /**
     * Checks if task at index n in taskList
     * is due/happening today.
     * @param taskList taskList provides access to Task.
     * @param ui ui provides user command.
     * @param storage Not needed.
     * @throws DukeException when task is not found in taskLst.
     */
    @Override
    public void execute(TaskList taskList, UI ui, Storage storage) throws DukeException {
        int n = Parser.getTaskNumber(ui.getCurrentInput(), taskList.getSize());
        if (taskList.checkIsToday(n)) {
            ui.showMessage("Yes due today");
        } else {
            ui.showMessage("No not due today");
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
