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
     * @return The message after executing.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        String message = "";
        for (int i = 0; i < indices.length; i++) {
            try {
                tasks.get(indices[i]).setDone();
                message += ui.showMarkStatus(tasks.get(indices[i]));
                message += "\n";
            } catch (IndexOutOfBoundsException e) {
                message += ui.showIndexOutOfBounds(indices[i]);
            }
        }
        return message;
    }
}
