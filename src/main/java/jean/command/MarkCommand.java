package jean.command;

import jean.storage.Storage;
import jean.task.TaskList;
import jean.ui.Ui;

/**
 * Represents a Command which instructs Jean to mark a task as done from a list of tasks.
 */
public class MarkCommand extends Command {
    private int taskIndex;

    /**
     * Creates a MarkCommand object with the task to be marked.
     *
     * @param taskIndex The index of the task to be marked as done.
     */
    public MarkCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    /**
     * Marks the task of the command as done from the list of tasks.
     *
     * @param taskList List of tasks which includes the task to be marked.
     * @param ui The ui object that prints feedback to the user interface.
     * @param storage The storage object that saves to or extracts from data.
     * @return The string to be printed.
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        return taskList.markTask(this.taskIndex, ui);
    }
}
