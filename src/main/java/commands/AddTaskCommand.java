package commands;

import duke.Task;
import duke.TaskList;
import duke.Ui;

/**
 * Command to add a new Task.
 */
public class AddTaskCommand extends Command {

    private TaskList tasks;
    private Task task;
    private Ui ui;

    /**
     * Returns a new AddTaskCommand.
     * @param tasks TaskList to add the Task to.
     * @param task Task to be added.
     * @param ui User Interface that prints a message to the user.
     */
    public AddTaskCommand(TaskList tasks, Task task, Ui ui) {
        this.tasks = tasks;
        this.task = task;
        this.ui = ui;
    }

    /**
     * Executes the command, and returns a String
     * describing the execution of this Command.
     * @return A String describing the task that was added.
     */
    public String execute() {
        tasks.add(task);
        return ui.showAddedTask(tasks);
    }
}