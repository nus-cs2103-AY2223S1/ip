package yilia.command;

import yilia.Storage;
import yilia.Ui;
import yilia.task.Task;
import yilia.task.TaskList;

import java.util.Arrays;

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
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        Arrays.sort(indices);
        for (int i = 0; i < indices.length / 2; i++) {
            int temp = indices[i];
            indices[i] = indices[indices.length - 1 - i];
            indices[indices.length - 1 - i] = temp;
        }
        for (int i = 0; i < indices.length; i++) {
            try {
                Task task = tasks.remove(indices[i]);
                ui.showDeleteStatus(task, tasks);
            } catch (IndexOutOfBoundsException e) {
                ui.showIndexOutOfBounds(indices[i]);
            }
        }
    }
}
