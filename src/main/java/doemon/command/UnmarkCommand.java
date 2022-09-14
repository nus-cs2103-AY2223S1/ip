package doemon.command;

import doemon.storage.Storage;
import doemon.task.Task;
import doemon.task.TaskList;
import doemon.ui.Ui;

/**
 * Command to unmark a task given a specified index.
 */
public class UnmarkCommand extends Command {
    /** Index of the task to be unmarked. */
    private int taskIndex;

    /**
     * Constructor for UnmarkCommand.
     *
     * @param taskIndex Index of the task to be unmarked.
     */
    public UnmarkCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        Task task = tasks.unmarkTask(this.taskIndex);
        ui.showUnmarkTask(task);
        storage.unmarkTaskData(this.taskIndex);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
