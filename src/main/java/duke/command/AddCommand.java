package duke.command;

import duke.exception.DukeException;
import duke.Response;
import duke.Storage;
import duke.TaskList;

import duke.task.Task;

/**
 * Represents a command to add a task.
 */
public class AddCommand extends Command {

    private Task task;

    /**
     * Initialises the AddCommand with the task to be added.
     * @param task Task to be added.
     */
    public AddCommand(Task task) {
        this.task = task;
    }

    /**
     * Executes this command to add a task to the list and update storage.
     * @param tasks Task list that task is added to.
     * @param storage Storage to be updated with newly added task.
     * @return The response of the execution.
     * @throws DukeException If unable to write to storage file.
     */
    @Override
    public Response execute(TaskList tasks, Storage storage) throws DukeException {
        tasks.addTask(this.task);
        storage.update(tasks);
        String message = "Got it. I've added this task:\n  " + task.toString() +
                "\nNow you have " + tasks.getNumTasks() + " tasks in the list.";
        return new Response(message, false);
    };
}
