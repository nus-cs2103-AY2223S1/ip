package command;

import exceptions.DukeException;
import storage.Storage;
import tasklist.TaskList;
import ui.UI;
import utility.Parser;

/**
 * Unmarks given task, and updates
 * status in TaskList and Storage.
 */
public class UnmarkCommand extends Command {
    private String[] slicedUserCommands;

    public UnmarkCommand(String[] slicedUserInput) {
        this.slicedUserCommands = slicedUserInput;
    }
    @Override
    public void execute(TaskList taskList, UI ui, Storage storage) throws DukeException {
        int n = Parser.getTaskNumber(ui.getCurrentInput(), taskList.getSize());
        taskList.unmarkTask(n);
        String storeLine = taskList.getTask(n).toString() + "\n";
        if (storage.isLineChanged(n, storeLine)) {
            ui.showMessage("unmarked task");
        }
    }
}
