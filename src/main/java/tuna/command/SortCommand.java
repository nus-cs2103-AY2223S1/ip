package tuna.command;

import tuna.utility.Storage;
import tuna.utility.TaskList;
import tuna.utility.Ui;

/**
 * Represents a Sort command.
 */
public class SortCommand extends Command {
    /**
     * Creates a SortCommand object.
     */
    public SortCommand() {
        super(CommandType.SORT);
    }

    /**
     * Executes the sort command, sorting all tasks according to chronological order.
     *
     * @param tasks   TaskList object.
     * @param ui      Ui object.
     * @param storage Storage object.
     */
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.sort();
        return ui.sortTasksMessage(tasks.listTasks());
    }
}
