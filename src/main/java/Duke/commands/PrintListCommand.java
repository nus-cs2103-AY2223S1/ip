package Duke.commands;

import Duke.storage.Storage;
import Duke.tasks.TaskList;
import Duke.ui.UI;

public class PrintListCommand extends Command {

    @Override
    public void execute(TaskList taskList, UI ui, Storage storage) {
        taskList.printList();
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
