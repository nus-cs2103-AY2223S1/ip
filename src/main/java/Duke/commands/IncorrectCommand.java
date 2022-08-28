package Duke.commands;

import Duke.storage.Storage;
import Duke.tasks.TaskList;
import Duke.ui.UI;

public class IncorrectCommand extends Command {

    @Override
    public void execute(TaskList tasks, UI ui, Storage storage) {
        ui.incorrectCommandMessage();
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
