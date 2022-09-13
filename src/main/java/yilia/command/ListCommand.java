package yilia.command;

import yilia.task.TaskList;
import yilia.util.Storage;
import yilia.util.Ui;

/**
 * Represents a command to show the whole list of tasks.
 */
public class ListCommand extends Command {
    public ListCommand(boolean isExit) {
        super(isExit);
    }
    /**
     * Executes the list command.
     *
     * @param tasks The tasks.
     * @param ui The use interface.
     * @param storage The local storage.
     * @return The message after executing.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return ui.showTask(tasks);
    }
}
