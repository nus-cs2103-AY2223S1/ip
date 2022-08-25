package ted.command;

import ted.storage.Storage;
import ted.task.TaskList;
import ted.ui.Ui;

public class ByeCommand extends Command {
    @Override
    public void execute(TaskList tasks, Ui ui, Storage st) {
        ui.showExit();
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
