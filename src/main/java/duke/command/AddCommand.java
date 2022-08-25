package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.task.Task;

/**
 * The AddCommand class represents a Command that adds a Task to the TaskList.
 *
 * @author Edric Yeo
 */
public class AddCommand extends Command {

    private Task task;

    /**
     * Constructor for a AddCommand instance, given a Task to be added.
     *
     * @param task The Task to be added to the TaskList.
     */
    public AddCommand(Task task) {
        this.task = task;
    }

    /**
     * Method that adds the Task to the TaskList.
     *
     * @param tasks   The TaskList instance for the task manager.
     * @param ui      The Ui instance for the task manager.
     * @param storage The Storage instance for the task manager.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.addTask(this.task);
        ui.showAddTask(this.task, tasks);
    }

    /**
     * Method that returns the String representation of an AddCommand.
     *
     * @return String representation of an AddCommand.
     */
    @Override
    public String toString() {
        return "Add command of task: " + task.toString();
    }
}
