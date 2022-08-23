package command;

import exceptions.DukeException;
import storage.Storage;
import tasklist.TaskList;
import ui.UI;
import utility.Parser;

public class GetLongDescriptionCommand extends Command{
    @Override
    public void execute(TaskList taskList, UI ui, Storage storage) throws DukeException {
        int n = Parser.getTaskNumber(ui.currentInput, taskList.getSize());
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
