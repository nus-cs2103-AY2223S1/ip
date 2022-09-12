package tuna.command;

import tuna.TunaException;
import tuna.utility.Storage;
import tuna.utility.TaskList;
import tuna.utility.Ui;

/**
 * Represents a mark item command. A MarkItemCommand contains the index of the task to be marked.
 */
public class MarkItemCommand extends Command {

    /** Index of the task to be marked */
    private int index;

    /**
     * Creates a MarkItemCommand object.
     *
     * @param index Index of the task to be marked.
     */
    public MarkItemCommand(int index) {
        super(CommandType.MARK);
        this.index = index - 1;
    }

    /**
     * Executes the mark item command, marking the item at the specified index as done.
     *
     * @param tasks TaskList object.
     * @param ui Ui object.
     * @param storage Storage object.
     * @throws TunaException Exception thrown when index provided is out of range.
     */
    public String execute(TaskList tasks, Ui ui, Storage storage) throws TunaException {
        if (index < 0 || index >= tasks.getTotalTasks()) {
            throw new TunaException("Oops! Seems like the index you entered is out of range");
        }
        assert index >= 0;
        assert index < tasks.getTotalTasks();
        tasks.markItem(index);
        return ui.markTaskMessage(tasks.getTask(index));
    }
}
