package sally.command;

import sally.storage.Storage;
import sally.task.TaskList;
import sally.ui.Ui;

/**
 * ListCommand class to represent command to display all tasks.
 *
 * @author liviamil
 */
public class ListCommand extends Command {
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showList(tasks);
    }

    @Override
    public boolean isBye() {
        return false;
    }
}
