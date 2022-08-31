package ava.task;

import ava.Ui;
import ava.processor.Storage;
import ava.processor.TaskList;

/**
 * Class to represent "Delete" tasks.
 */
public class Delete extends Task {
    private int num;

    /**
     * The constructor for Delete task
     *
     * @param num Index of the task to be deleted.
     */
    public Delete(int num) {
        super("delete", false);
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
}
