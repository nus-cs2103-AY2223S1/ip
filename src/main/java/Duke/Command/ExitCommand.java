package Duke.Command;

import Duke.TaskList;
import Duke.Ui;
import Duke.FileStorage.Storage;

/**
 * This class represents the exit command that allow the user
 * to quit Duke.
 */
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
