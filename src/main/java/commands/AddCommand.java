package commands;

import byu.TaskList;
import byu.Ui;

import task.Task;

/**
 * Represents a command to add a task to the list.
 */
public class AddCommand extends Command {

    private Task task;

    /**
     * Creates an AddCommand with the Task to be added.
     *
     * @param task the Task to be added.
     */
    public AddCommand(Task task) {
        this.task = task;
    }

    public void execute(TaskList tasks, Ui ui) {
        tasks.addTask(task);
    }

    public boolean isExit() {
        return false;
    }
}
