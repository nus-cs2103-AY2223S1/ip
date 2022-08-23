package command;

import exceptions.DukeException;
import storage.Storage;
import tasklist.TaskList;
import ui.UI;

public class ListCommand extends Command{
    @Override
    public void execute(TaskList taskList, UI ui, Storage storage) throws DukeException {
        ui.showMessage(taskList.getContents());
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
