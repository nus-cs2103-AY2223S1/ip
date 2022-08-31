package blink.command;

import blink.Storage;
import blink.TaskList;
import blink.Ui;
import blink.task.Task;

/**
 * Delete command that will delete a task from the TaskList
 */
public class DeleteCommand extends Command {

    private int num;

    /**
     * Constructor of the DeleteCommand.
     *
     * @param num Position of task from TaskList to remove
     */
    public DeleteCommand(int num) {
        this.num = num;
    }

    /**
     * Causes task at specified number position in TaskList to be
     * deleted, then Ui will display information of the deleted task
     * which is then removed from Storage.
     *
     * @param tasks TaskList object of current Blink object
     * @param ui Ui object of current Blink object
     * @param storage Storage object of current Blink object
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage)  {
        Task temp = tasks.deleteTask(num);
        storage.save(tasks);
        return ui.deleteTask(tasks, temp);
    }

    /**
     * DeleteCommand does not end the program thus returns false.
     *
     * @return False.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
