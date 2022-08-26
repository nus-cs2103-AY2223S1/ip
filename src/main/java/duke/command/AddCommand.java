package duke.command;

import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Command to add a task to the task list.
 */
public class AddCommand extends Command {
    private Task task;

    /**
     * Constructor for <code>AddCommand</code>.
     * @param task
     */
    public AddCommand(Task task) {
        this.task = task;
    }

    /**
     * Adds a task into the task list.
     * @param tasks
     * @param ui
     * @param storage
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.addTask(task);
        ui.showAddTask(task, tasks.getNumberOfTasks());
    }

    /**
     * Checks if the command exit duke.
     * @return
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
