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
    private String[] slicedUserCommands;

    public MarkCommand(String[] slicedUserInput) {
        this.slicedUserCommands = slicedUserInput;
    }
    @Override
    public void execute(TaskList taskList, UI ui, Storage storage) throws DukeException {
        int n = Parser.getTaskNumber(ui.getCurrentInput(), taskList.getSize());
        taskList.markTask(n);
        String storeLine = taskList.getTask(n).toString() + "\n";
        if (storage.isLineChanged(n, storeLine)) {
            ui.showMessage("marked task");
        }
    }
}
