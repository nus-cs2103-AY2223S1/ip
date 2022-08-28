package Duke.commands;

import Duke.storage.Storage;
import Duke.tasks.TaskList;
import Duke.ui.UI;

public class ExitCommand extends Command {

    @Override
    public void execute(TaskList taskList, UI ui, Storage storage) {
        ui.goodbyeMessage();
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
