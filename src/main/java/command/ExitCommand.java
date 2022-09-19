package command;

import exception.DorisException;
import storage.Storage;
import task.TaskList;
import ui.Ui;

/**
 * Encapsulates a user instruction to exit the bot.
 *
 * @author Marcus Low
 */
public class ExitCommand extends Command {

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            storage.save(tasks);
            return ui.showBye();
        } catch (DorisException e) {
            return ui.showError(e);
        }
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
