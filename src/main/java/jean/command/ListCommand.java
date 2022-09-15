package jean.command;

import jean.storage.Storage;
import jean.task.TaskList;
import jean.ui.Ui;

/**
 * Represents a Command which instructs Jean to print all the tasks from a list of tasks.
 */
public class ListCommand extends Command {

    /**
     * Lists all the tasks from the list of tasks.
     *
     * @param taskList List of tasks to be listed.
     * @param ui The ui object that prints feedback to the user interface.
     * @param storage The storage object that saves to or extracts from data.
     * @return The string to be printed.
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        return taskList.listTask(ui);
    }
}
