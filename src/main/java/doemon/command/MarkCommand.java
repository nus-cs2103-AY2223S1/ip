package doemon.command;

import doemon.storage.Storage;
import doemon.task.Task;
import doemon.task.TaskList;
import doemon.ui.Ui;

/**
 * Command to mark a task given a specified index.
 */
public class MarkCommand extends Command {
    /** Index of the task to be marked. */
    private int taskIndex;

    /**
     * Constructor for MarkCommand.
     *
     * @param taskIndex Index of task to be marked.
     */
    public MarkCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        Task task = tasks.markTask(this.taskIndex);
        ui.showMarkTask(task);
        storage.markTaskData(this.taskIndex);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
