package command;

import exceptions.DukeException;
import storage.Storage;
import tasklist.TaskList;
import ui.UI;
import utility.Parser;

/**
 * Marks given task, and updates
 * status in TaskList and Storage.
 */
public class MarkCommand extends Command {
    private int index;

    public MarkCommand(int index) {
        this.index = index;
    }
    @Override
    public void execute(TaskList taskList, UI ui, Storage storage) throws DukeException {
        taskList.markTask(index);
        String storeLine = taskList.getTask(index).toString() + "\n";
        if (storage.isLineChanged(index, storeLine)) {
            ui.showMessage("marked task");
        }
    }
}
