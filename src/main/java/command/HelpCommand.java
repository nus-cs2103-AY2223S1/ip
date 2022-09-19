package command;

import storage.Storage;
import task.TaskList;
import ui.Ui;

/**
 * Encapsulates a user instruction to display list of possible instructions.
 *
 * @author Marcus Low
 */
public class HelpCommand extends Command {

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return ui.showCommands();
    }
}
