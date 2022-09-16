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
     * @return the message to be shown by UI
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        taskList.markAsDone(index);
        storage.save(taskList);
        return ui.mark(taskList, index);
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
