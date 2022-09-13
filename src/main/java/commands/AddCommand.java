package commands;

import byu.TaskList;
import byu.Ui;
import exceptions.DuplicateException;
import task.Task;

/**
 * A command to add a task to the list.
 */
public class AddCommand extends Command {

    private final Task task;

    /**
     * Creates a command to add a task.
     *
     * @param task the Task to be added.
     */
    public AddCommand(Task task) {
        this.task = task;
    }

    @Override
    public void execute(TaskList tasks, Ui ui) throws DuplicateException {
        tasks.addTask(task);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
