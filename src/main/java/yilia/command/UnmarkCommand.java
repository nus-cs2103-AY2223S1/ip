package yilia.command;

import yilia.Storage;
import yilia.Ui;
import yilia.task.TaskList;

/**
 * Represents a command to unmark a task as not done.
 */
public class UnmarkCommand extends Command {
    private final int[] indices;

    public UnmarkCommand(int ...indices) {
        this.indices = indices;
    }
    /**
     * Executes the unmark command.
     *
     * @param tasks The tasks.
     * @param ui The use interface.
     * @param storage The local storage.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        for (int i = 0; i < indices.length; i++) {
            try {
                tasks.get(indices[i]).setNotDone();
                ui.showUnmarkStatus(tasks.get(indices[i]));
            } catch (IndexOutOfBoundsException e) {
                ui.showIndexOutOfBounds(indices[i]);
            }
        }
    }
}
