package command;

import exceptions.DukeException;
import storage.Storage;
import tasklist.TaskList;
import ui.UI;

public class DeleteTaskCommand extends Command{
    @Override
    public void execute(TaskList taskList, UI ui, Storage storage) throws DukeException {

    }

    @Override
    public boolean isExit() {
        return false;
    }
}
