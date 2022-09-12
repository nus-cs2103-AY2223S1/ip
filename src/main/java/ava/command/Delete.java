package ava.command;

import ava.Ui;
import ava.task.Task;
import ava.util.Storage;
import ava.util.TaskList;

/**
 * Class to represent "Delete" tasks.
 */
public class Delete extends Command {
    private final int num;

    /**
     * The constructor for Delete command.
     *
     * @param num Index of the task to be deleted.
     */
    public Delete(int num) {
        this.num = num;
    }

    /**
     * Executes process to delete a specific task.
     *
     * @param tasks TaskList.
     * @param ui Class to print the ui.
     * @param storage Class to read/write commands to file.
     * @return The response of the command.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        Task taskDeleted = tasks.delete(num);
        storage.write(tasks.getTasks());
        return ui.showDeleteTask(tasks, taskDeleted);
    }

    /**
     * Tests if a command is exit.
     *
     * @return False.
     */
    @Override
    public boolean isBye() {
        return false;
    }
}
