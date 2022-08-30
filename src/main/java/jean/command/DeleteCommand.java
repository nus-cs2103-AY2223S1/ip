package jean.command;

import java.io.IOException;

import jean.storage.Storage;
import jean.task.TaskList;
import jean.ui.Ui;

/**
 * Represents a Command which instructs Jean to delete a task from the list of tasks.
 */
public class DeleteCommand extends Command {
    private int taskIndex;

    /**
     * Creates a DeleteCommand object with the task to be added.
     *
     * @param taskIndex The index of the task to be deleted.
     */
    public DeleteCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    /**
     * Deletes the task of the command from the list of tasks.
     *
     * @param taskList List of tasks to be deleted from.
     * @param ui The ui object that prints feedback to the user interface.
     * @param storage The storage object that saves to or extracts from data.
     * @return The string to be printed.
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) throws IOException {
        return taskList.deleteTask(this.taskIndex, ui);
    }
}
