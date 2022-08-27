/**
 * This class handles all commands related to adding tasks
 * and inherits from the Command class.
 */
package duke;

public class AddCommand extends Command {
    private Task task;

    /**
     * Constructor for the AddCommand class.
     *
     * @param task The task to be added.
     */
    public AddCommand(Task task) {
        this.task = task;
    }

    /**
     * {@inheritDoc}
     * Adds a task to the tasklist.
     */
    @Override
    public TaskList execute(TaskList taskList, Ui ui, Storage storage) {
        taskList.addTask(task);
        ui.displayTask(ui.ADDED, task);
        ui.showTotalTasks(taskList);
        storage.save(taskList, ui);
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
