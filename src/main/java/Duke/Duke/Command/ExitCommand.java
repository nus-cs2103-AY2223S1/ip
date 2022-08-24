package Duke.Command;

import Duke.TaskList;
import Duke.Ui;
import Duke.FileStorage.Storage;

public class ExitCommand extends Command{

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showExit();
    }

    @Override 
    public boolean isExit() {
        return true;
    }
}
