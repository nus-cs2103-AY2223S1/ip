package commands;

import tasks.TaskList;
import ui.Ui;
import storage.Storage;

/**
 * Encapsulates a user instruction to display list of saved tasks.
 *
 * @author fannyjian
 */
public class ListCommand extends Command {
    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showList(tasks);
    }
}
