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
     * @return String message = "";
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        String message = "";
        for (int i = 0; i < indices.length; i++) {
            try {
                tasks.get(indices[i]).setNotDone();
                message += ui.showUnmarkStatus(tasks.get(indices[i]));
                message += "\n";
            } catch (IndexOutOfBoundsException e) {
                message += ui.showIndexOutOfBounds(indices[i]);
            }
        }
        return message;
    }
}
