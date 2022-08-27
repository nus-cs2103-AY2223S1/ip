/**
 * This class handles all commands related to unmarking tasks
 * and inherits from the Command class.
 */
package duke;

public class UnmarkCommand extends Command {
    /** Index of task to be marked */
    int index;

    /**
     * Constructor for the UnmarkCommand class.
     *
     * @param index to indicate the task to be unmarked.
     */
    public UnmarkCommand(int index) {
        this.index = index;
    }

    /**
     * {@inheritDoc}
     * Unmarks a task from the tasklist.
     */
    @Override
    public TaskList execute(TaskList taskList, Ui ui, Storage storage) {

        taskList.unmarkTask(index);
        Task temp = taskList.getTask(index);

        ui.displayTask(ui.UNMARKED, temp);
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
