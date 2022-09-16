package yilia.command;

import yilia.task.TaskList;
import yilia.util.Storage;
import yilia.util.Ui;

/**
 * Represents a command to show the whole list of tasks.
 */
public class FindCommand extends Command {
    private final String content;
    public FindCommand(String content) {
        this.content = content;
    }
    /**
     * Executes the find command.
     *
     * @param tasks The tasks.
     * @param ui The use interface.
     * @param storage The local storage.
     * @return The message after executing.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        TaskList filteredTasks = tasks.filter(content);
        return ui.showTask(filteredTasks);
    }
}
