package cheese.command;

import cheese.data.TaskList;
import cheese.storage.Storage;
import cheese.ui.Ui;

/**
 * Represents a user command to display the list.
 */
public class ListCommand extends Command {
    /**
     * Executes operation to display the list.
     * 
     * @param {@inheritDoc}
     */
    @Override
    public void execute(TaskList taskList, Storage storage, Ui ui) {
        ui.showTaskList(taskList);
    }
}
