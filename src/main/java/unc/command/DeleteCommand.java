package unc.command;

import unc.Storage;
import unc.TaskList;
import unc.Ui;
import unc.task.Task;

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
     * @return the message to be shown by UI
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        Task tempTask = taskList.get(index);
        taskList.delete(index);
        storage.save(taskList);
        return ui.delete(tempTask, taskList.size());
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
