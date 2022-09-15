package doemon.command;

import doemon.response.Response;
import doemon.storage.Storage;
import doemon.task.Task;
import doemon.task.TaskList;

/**
 * Command to add a new task.
 */
public class AddCommand extends Command {
    /** The task to be added. */
    private Task task;

    /**
     * Constructor for AddCommand.
     *
     * @param task Task to be added.
     */
    public AddCommand(Task task) {
        this.task = task;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String execute(TaskList tasks, Response response, Storage storage) {
        tasks.addTask(task);
        storage.addTaskData(task);
        return response.addTaskString(task, tasks.getSize());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
