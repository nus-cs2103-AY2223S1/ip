package jean.command;

import jean.storage.Storage;
import jean.task.TaskList;
import jean.ui.Ui;

/**
 * Represents a Command which instructs Jean to sort the list of tasks.
 */
public class SortCommand extends Command {

    /**
     * Creates a SortCommand object to sort all the tasks.
     */
    public SortCommand() {
    }

    /**
     * Sorts the list of tasks.
     *
     * @param taskList List of tasks to be sorted.
     * @param ui The ui object that prints feedback to the user interface.
     * @param storage The storage object that saves to or extracts from data.
     * @return The string to be printed.
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        return taskList.sortTask(ui);
    }
}
