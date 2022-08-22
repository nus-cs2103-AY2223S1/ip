package sky.command;

import sky.Storage;
import sky.TaskList;
import sky.Ui;

/**
 * The ExitCommand class deals with exiting the program.
 */
public class ExitCommand extends Command {
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        ui.endConvo();
    }

    @Override
    public boolean isExit() {
        return true;
    }
}