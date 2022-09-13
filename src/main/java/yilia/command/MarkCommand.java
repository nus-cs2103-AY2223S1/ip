package yilia.command;

import yilia.task.TaskList;
import yilia.util.Storage;
import yilia.util.Ui;

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
        StringBuilder message = new StringBuilder();
        for (int index : indices) {
            try {
                tasks.get(index).setDone();
                message.append(ui.showMarkStatus(tasks.get(index)));
                message.append("\n");
            } catch (IndexOutOfBoundsException e) {
                message.append(ui.showIndexOutOfBounds(index));
            }
        }
        return message.toString();
    }
}
