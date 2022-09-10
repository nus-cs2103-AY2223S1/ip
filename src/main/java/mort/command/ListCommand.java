package mort.command;

import mort.storage.Storage;
import mort.task.TaskList;
import mort.ui.Ui;

/**
 * Represents a command to list all tasks in the task list.
 */
public class ListCommand extends Command {
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return ui.getListMessage(tasks.list());
    }
}
