package jean.command;

import jean.storage.Storage;
import jean.task.TaskList;
import jean.ui.Ui;

/**
 * Represents a Command which instructs Jean to unmark a task from a list of tasks.
 */
public class UnmarkCommand extends Command {
    private int taskIndex;

    /**
     * Creates a UnmarkCommand object with the task to be unmarked.
     *
     * @param taskIndex The index of the task to be unmarked.
     */
    public UnmarkCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    /**
     * Unmarks the task of the command from the list of tasks.
     *
     * @param taskList List of tasks which includes the task to be unmarked.
     * @param ui The ui object that prints feedback to the user interface.
     * @param storage The storage object that saves to or extracts from data.
     * @return The string to be printed.
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        return taskList.unmarkTask(this.taskIndex, ui);
    }
}
