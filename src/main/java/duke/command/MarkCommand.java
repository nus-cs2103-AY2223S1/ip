package duke.command;

import duke.exception.DukeException;
import duke.Response;
import duke.Storage;
import duke.TaskList;

import duke.task.Task;

/**
 * Represents a command to mark a task as done.
 */
public class MarkCommand extends Command {


    private int taskNumber;

    /**
     * Initialises the MarkCommand with the task number of task to be marked.
     * @param taskNumber Task number of task to be marked.
     */
    public MarkCommand(int taskNumber) {
        this.taskNumber = taskNumber;
    }

    /**
     * Executes this command to mark a task as done and update storage.
     * @param tasks Task list that task at task number is retrieved from.
     * @param storage Storage to be updated after marking task.
     * @return The response of the execution.
     * @throws DukeException If unable to write to storage file.
     */
    @Override
    public Response execute(TaskList tasks, Storage storage) throws DukeException {
        Task task = tasks.getTask(taskNumber);
        task.markAsDone();
        storage.update(tasks);
        String message = "Nice! I've marked this task as done:\n  " + task.toString();
        return new Response(message, false);
    };
}
