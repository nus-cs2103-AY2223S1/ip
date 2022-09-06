package yilia.command;

import java.util.Arrays;

import yilia.Storage;
import yilia.Ui;
import yilia.task.Task;
import yilia.task.TaskList;

/**
 * Represents a command to delete a task from the task list.
 */
public class DeleteCommand extends Command {
    private final int[] indices;

    public DeleteCommand(int ...indices) {
        this.indices = indices;
    }
    /**
     * Executes the delete command.
     *
     * @param tasks The tasks.
     * @param ui The use interface.
     * @param storage The local storage.
     * @return The message after executing.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        Arrays.sort(indices);
        for (int i = 0; i < indices.length / 2; i++) {
            int temp = indices[i];
            indices[i] = indices[indices.length - 1 - i];
            indices[indices.length - 1 - i] = temp;
        }
        String message = "";
        for (int i = 0; i < indices.length; i++) {
            try {
                Task task = tasks.remove(indices[i]);
                message += ui.showDeleteStatus(task, tasks);
            } catch (IndexOutOfBoundsException e) {
                message += ui.showIndexOutOfBounds(indices[i]);
                message += "\n";
            }
        }
        return message;
    }
}
