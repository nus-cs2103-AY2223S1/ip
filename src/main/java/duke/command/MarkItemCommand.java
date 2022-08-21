package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

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
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.markItem(index);
        ui.printMarkTaskMessage(tasks.getTask(index));
    }
}
