package duke.command;

import duke.exception.DukeException;
import duke.Response;
import duke.Storage;
import duke.TaskList;

import duke.task.Task;

/**
 * Represents a command to delete a task.
 */
public class DeleteCommand extends Command {

    private int taskNumber;

    /**
     * Initialises the DeleteCommand with the task number of task to be deleted.
     * @param taskNumber Task number of task to be deleted.
     */
    public DeleteCommand(int taskNumber) {
        this.taskNumber = taskNumber;
    }

    /**
     * Executes this command to delete a task from the list and update storage.
     * @param tasks Task list that task at task number is deleted from.
     * @param storage Storage to be updated after deleting task.
     * @return The response of the execution.
     * @throws DukeException If unable to write to storage file.
     */
    @Override
    public Response execute(TaskList tasks, Storage storage) throws DukeException {
        Task deletedTask = tasks.deleteTask(this.taskNumber);
        storage.update(tasks);
        String message = "Noted. I've removed this task:\n  " + deletedTask.toString() +
                "\nNow you have " + tasks.getNumTasks() + " tasks in the list.";
        return new Response(message, false);
    };
}
