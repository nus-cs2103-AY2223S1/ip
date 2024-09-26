package tuna.command;

import tuna.TunaException;
import tuna.utility.Storage;
import tuna.utility.TaskList;
import tuna.utility.Ui;

/**
 * Represents a un-mark item command. A UnMarkItemCommand contains the index of the task to be un-marked.
 */
public class UnMarkItemCommand extends Command {

    /** Index of the task to be un-marked */
    private int index;

    /**
     * Creates an UnMarkItemCommand object.
     *
     * @param index Index of the task to be un-marked.
     */
    public UnMarkItemCommand(int index) {
        super(CommandType.UNMARK);
        this.index = index - 1;
    }

    /**
     * Executes the un-mark item command, marking the item at the specified index as not done.
     *
     * @param tasks TaskList object.
     * @param ui Ui object.
     * @param storage Storage object.
     */
    public String execute(TaskList tasks, Ui ui, Storage storage) throws TunaException {
        if (index < 0 || index >= tasks.getTotalTasks()) {
            throw new TunaException("Oops! Seems like the index you entered is out of range");
        }
        assert index >= 0;
        assert index < tasks.getTotalTasks();
        tasks.unMarkItem(index);
        return ui.unMarkTaskMessage(tasks.getTask(index));
    }
}
