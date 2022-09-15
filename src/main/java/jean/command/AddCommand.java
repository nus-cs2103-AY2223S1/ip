package jean.command;

import jean.storage.Storage;
import jean.task.Task;
import jean.task.TaskList;
import jean.ui.Ui;

/**
 * Represents a Command which instructs Jean to add a task into the list of tasks.
 */
public class AddCommand extends Command {
    private Task task;

    /**
     * Creates a AddCommand object with the task to be added.
     *
     * @param task The task to be added.
     */
    public AddCommand(Task task) {
        this.task = task;
    }

    /**
     * Adds the task of the command to the list of tasks.
     *
     * @param taskList List of tasks to be added to.
     * @param ui The ui object that prints feedback to the user interface.
     * @param storage The storage object that saves to or extracts from data.
     * @return The string to be printed.
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        return taskList.addTask(this.task, ui);
    }
}
