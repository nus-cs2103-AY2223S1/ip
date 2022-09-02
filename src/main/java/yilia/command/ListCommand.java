package yilia.command;

import yilia.Storage;
import yilia.Ui;
import yilia.task.TaskList;

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
        String message = "";
        for (int i = 1; i <= tasks.size(); i++) {
            message += ui.showTask(i, tasks);
            message += "\n";
        }
        return message;
    }
}
