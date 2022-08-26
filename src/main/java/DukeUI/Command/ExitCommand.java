package DukeUI.Command;

import DukeUI.TaskList;
import DukeUI.Ui;
import DukeUI.FileStorage.Storage;

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
