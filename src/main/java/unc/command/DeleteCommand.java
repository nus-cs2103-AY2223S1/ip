package unc.command;

import unc.Storage;
import unc.task.Task;
import unc.TaskList;
import unc.Ui;

/**
 * Command that deletes a task from list when executed.
 */
public class DeleteCommand extends Command {
    private final int index;

    /**
     * Constructor.
     *
     * @param num Index of task as seen by user, not the actual index in ArrayList structure.
     */
    public DeleteCommand(int num) {
        this.index = num - 1;
    }

    /**
     * Deletes the task at a given index.
     * Saves updated list.
     *
     * @param taskList {@inheritDoc}
     * @param ui {@inheritDoc}
     * @param storage {@inheritDoc}
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        Task tempTask = taskList.get(index);
        taskList.delete(index);
        ui.delete(tempTask, taskList.size());
        storage.save(taskList);
    }

    /**
     * {@inheritDoc}
     *
     * @return false.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
