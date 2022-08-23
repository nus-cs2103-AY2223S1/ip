package command;

import exceptions.DukeException;
import storage.Storage;
import tasklist.TaskList;
import ui.UI;
import utility.Parser;

public class MarkCommand extends Command{
    @Override
    public void execute(TaskList taskList, UI ui, Storage storage) throws DukeException {
        int n = Parser.getTaskNumber(ui.currentInput, taskList.getSize());
        String storeLine = taskList.markTask(n);
        storage.changeLine(n, storeLine);
        ui.showMessage("marked task");
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
