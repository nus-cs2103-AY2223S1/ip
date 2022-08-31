package yilia.command;

import yilia.Storage;
import yilia.Ui;
import yilia.task.TaskList;

/**
 * Represents a command to show the whole list of tasks.
 */
public class FindCommand extends Command {
    private String content;
    public FindCommand(String content) {
        this.content = content;
    }
    /**
     * Executes the find command.
     *
     * @param tasks The tasks.
     * @param ui The use interface.
     * @param storage The local storage.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        for (int i = 1; i <= tasks.size(); i++) {
            if (tasks.get(i).toString().contains(content)) {
                ui.showTask(i, tasks);
            }
        }
    }
}
