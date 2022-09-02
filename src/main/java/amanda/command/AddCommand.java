package main.java.amanda.command;

import main.java.amanda.task.Task;
import main.java.amanda.ui.Ui;
import main.java.amanda.manager.TaskList;
import main.java.amanda.manager.StoreManager;

/**
 * AddCommand is a command that adds new tasks to the current task list.
 */
public class AddCommand extends Command {

    /**
     * Constructor for AddCommand class.
     */
    public AddCommand(Task task) {
        super(task, 0);
    }

    /**
     * Adds the task associated with this AddCommand to the current task list
     * and calls the ui to show the output to the user.
     * @param tasks the current task list.
     * @param ui the current Ui.
     * @param store the store manager that write any changes to the storage file.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, StoreManager store) {
        tasks.getList().add(this.task); // add the task to the current task list.
        store.store(tasks); // update storage with new task addition .
        ui.showAddCommand(tasks, task); // print messages for AddCommand.
    }
}
