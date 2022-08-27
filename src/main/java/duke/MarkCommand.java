/**
 * This class handles all commands related to marking tasks
 * and inherits from the Command class.
 */
package duke;

public class MarkCommand extends Command {
    /** Index of task to be marked */
    int index;

    /**
     * Constructor for the MarkCommand class.
     *
     * @param index to indicate the task to be marked.
     */
    public MarkCommand(int index) {
        this.index = index;
    }

    /**
     * {@inheritDoc}
     * Marks a task from the tasklist.
     */
    @Override
    public TaskList execute(TaskList taskList, Ui ui, Storage storage) {
        taskList.markTask(index);
        Task temp = taskList.getTask(index);
        ui.displayTask(ui.MARKED, temp);
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
