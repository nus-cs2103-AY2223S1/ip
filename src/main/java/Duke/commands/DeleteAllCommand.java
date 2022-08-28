package Duke.commands;

import Duke.storage.Storage;
import Duke.tasks.TaskList;
import Duke.ui.UI;

public class DeleteAllCommand extends Command {

    @Override
    public void execute(TaskList taskList, UI ui, Storage storage) {
        taskList.deleteAll();
        ui.deleteAllMessage();
        storage.store(taskList);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}

