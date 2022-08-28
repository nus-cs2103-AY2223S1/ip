package unc.command;

import unc.Storage;
import unc.TaskList;
import unc.Ui;

/**
 * Command that marks a task as done when executed.
 */
public class MarkCommand extends Command {
    private final int index;

    /**
     * Constructor.
     *
     * @param num Index of task as seen by user, not the actual index in ArrayList structure.
     */
    public MarkCommand(int num) {
        this.index = num - 1;
    }

    /**
     * Marks the task at given index as done.
     *
     * @param taskList List to be operated on.
     * @param ui UI to print message.
     * @param storage Storage to save updated list.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        taskList.markAsDone(index);
        ui.mark(taskList, index);
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
