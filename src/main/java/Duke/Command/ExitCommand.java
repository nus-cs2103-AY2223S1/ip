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
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return ui.getExitMsg();
    }

    @Override 
    public boolean isExit() {
        return true;
    }
}
