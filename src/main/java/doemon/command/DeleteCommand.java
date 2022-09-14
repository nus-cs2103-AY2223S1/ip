package doemon.command;

import doemon.storage.Storage;
import doemon.task.Task;
import doemon.task.TaskList;
import doemon.ui.Ui;

/**
 * Delete Command
 */
public class DeleteCommand extends Command {
    /** Index of the task to be deleted. */
    private int taskIndex;

    /**
     * Constructor for DeleteCommand.
     *
     * @param taskIndex Index of task to be deleted.
     */
    public DeleteCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        Task task = tasks.deleteTask(this.taskIndex);
        ui.showDeleteTask(task, tasks.getSize());
        storage.deleteTaskData(this.taskIndex);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
