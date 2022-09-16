package yilia.command;

import yilia.task.TaskList;
import yilia.util.Storage;
import yilia.util.Ui;

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
        StringBuilder message = new StringBuilder();
        for (int index : indices) {
            try {
                tasks.get(index).setNotDone();
                message.append(ui.showUnmarkStatus(tasks.get(index)));
                message.append("\n");
            } catch (IndexOutOfBoundsException e) {
                message.append(ui.showIndexOutOfBounds(index));
            }
        }
        return message.toString();
    }
}
