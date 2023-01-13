package duke.command;

import duke.exception.DukeException;
import duke.Response;
import duke.Storage;
import duke.TaskList;

import duke.task.Task;

/**
 * Represents a command to mark a task as not done.
 */
public class UnmarkCommand extends Command {

    private int taskNumber;

    /**
     * Initialises the UnmarkCommand with the task number of task to be unmarked.
     * @param taskNumber Task number of task to be unmarked.
     */
    public UnmarkCommand(int taskNumber) {
        this.taskNumber = taskNumber;
    }

    /**
     * Executes this command to mark a task as not done and update storage.
     * @param tasks Task list that task at task number is retrieved from.
     * @param storage Storage to be updated after unmarking task.
     * @return The response of the execution.
     * @throws DukeException If unable to write to storage file.
     */
    @Override
    public Response execute(TaskList tasks, Storage storage) throws DukeException {
        Task task = tasks.getTask(taskNumber);
        task.markAsNotDone();
        storage.update(tasks);
        String message = "OK, I've marked this task as not done yet:\n  " + task.toString();
        return new Response(message, false);
    };
}
