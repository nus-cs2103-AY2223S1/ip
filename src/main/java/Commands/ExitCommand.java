package Commands;

import Tasks.TaskList;
import Main.Ui;
import Main.Storage;

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
