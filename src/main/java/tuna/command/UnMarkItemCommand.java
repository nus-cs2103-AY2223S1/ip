package tuna.command;

import tuna.Storage;
import tuna.TaskList;
import tuna.Ui;

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
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.unMarkItem(index);
        return ui.unMarkTaskMessage(tasks.getTask(index));
    }
}
