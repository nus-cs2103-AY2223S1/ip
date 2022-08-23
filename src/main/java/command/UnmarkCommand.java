package command;

import exceptions.DukeException;
import storage.Storage;
import tasklist.TaskList;
import ui.UI;
import utility.Parser;

public class UnmarkCommand extends Command{
    @Override
    public void execute(TaskList taskList, UI ui, Storage storage) throws DukeException {
        int n = Parser.getTaskNumber(ui.currentInput, taskList.getSize());
        String storeLine = taskList.unmarkTask(n);
        storage.changeLine(n, storeLine);
        ui.showMessage("unmarked task");
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
