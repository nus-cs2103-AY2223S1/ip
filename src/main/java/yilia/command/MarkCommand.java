package yilia.command;

import yilia.Storage;
import yilia.Ui;
import yilia.task.TaskList;

/**
 * Represents a command to mark a task as done.
 */
public class MarkCommand extends Command {
    private final int[] indices;

    public MarkCommand(int... indices) {
        this.indices = indices;
    }
    /**
     * Executes the mark command.
     *
     * @param tasks The tasks.
     * @param ui The use interface.
     * @param storage The local storage.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        for (int i = 0; i < indices.length; i++) {
            try {
                tasks.get(indices[i]).setDone();
                ui.showMarkStatus(tasks.get(indices[i]));
            } catch (IndexOutOfBoundsException e) {
                ui.showIndexOutOfBounds(indices[i]);
            }
        }
    }
}
