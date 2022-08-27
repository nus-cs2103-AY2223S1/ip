/**
 * This class handles all commands related to deleting tasks
 * and inherits from the Command class.
 */

package duke;

public class DeleteCommand extends Command {
    /** Index of task to be deleted at */
    private int index;

    /**
     * Constructor for the DeleteCommand class.
     *
     * @param index to indicate the task to be deleted.
     */
    public DeleteCommand(int index) {
        this.index = index;
    }

    /**
     * {@inheritDoc}
     * Deletes a task from the tasklist.
     */
    @Override
    public TaskList execute(TaskList taskList, Ui ui, Storage storage) {
        Task temp = taskList.getTask(index);
        taskList.removeTask(index);
        storage.save(taskList, ui);
        ui.displayTask(ui.DELETED, temp);
        ui.showTotalTasks(taskList);
        return taskList;
    }

    /**
     * Returns false.
     *
     * @return false.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
