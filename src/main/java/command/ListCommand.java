package command;

import storage.Storage;
import task.TaskList;
import ui.Ui;

/**
 * Encapsulates a user instruction to display list of saved tasks.
 *
 * @author Marcus Low
 */
public class ListCommand extends Command {

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return ui.showList(tasks);
    }
}
