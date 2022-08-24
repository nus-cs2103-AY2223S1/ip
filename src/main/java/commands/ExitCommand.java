package commands;

import storage.Storage;
import tasks.TaskList;
import ui.Ui;


public class ExitCommand extends Command {
    @Override
    public boolean isExit() {
        return true;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.farewell();
    }
}
