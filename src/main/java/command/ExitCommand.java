package command;

import storage.Storage;
import task.TaskList;
import ui.Ui;


/**
 * Encapsulates a user instruction to exit the chatbot.
 *
 * @author fannyjian
 */
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
