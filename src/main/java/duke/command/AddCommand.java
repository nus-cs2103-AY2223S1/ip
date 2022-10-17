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
     * Creates a AddCommand instance, given a Task to be added.
     *
     * @param task The Task to be added to the TaskList.
     */
    public AddCommand(Task task) {
        this.task = task;
    }

    /**
     * Returns a message to indicate a Task has been added.
     * Adds the Task to the TaskList.
     *
     * @param tasks   The TaskList instance for the task manager.
     * @param ui      The Ui instance for the task manager.
     * @param storage The Storage instance for the task manager.
     * @return A message to indicate a Task has been added.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.addTask(this.task);
        return ui.showAddTask(this.task, tasks);
    }

    /**
     * Returns the String representation of an AddCommand.
     *
     * @return String representation of an AddCommand.
     */
    @Override
    public String toString() {
        return "Add command of task: " + task.toString();
    }
}
