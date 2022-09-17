package duke.command;

import duke.Storage;
import duke.exception.DukeException;
import duke.gui.Response;
import duke.gui.ResponseType;
import duke.task.Task;
import duke.task.TaskList;

/**
 * Represents a command that marks a specified task as not done.
 */
public class MarkTaskNotDoneCommand extends Command {
    /** The task number corresponding to the task to be marked not done */
    private final int taskNumber;

    /**
     * Constructs a MarkTaskNotDoneCommand.
     *
     * @param taskNumber The task number of the completed task.
     */
    public MarkTaskNotDoneCommand(int taskNumber) {
        this.taskNumber = taskNumber;
    }

    /**
     * Executes the MarkTaskNotDoneCommand, marks the task with the associated task
     * number in the specified task list as not done.
     *
     * @param tasks The task list which the task belongs to.
     * @param storage The storage manager that deals with loading and saving tasks to the hard disk.
     * @return A task type Response, containing the task, now marked as not completed, and a task
     *         marked uncompleted successfully message.
     * @throws DukeException If the task number is invalid (eg: Task does not exist/ task number
     *                       is not positive).
     */
    @Override
    public Response<Task> execute(TaskList tasks, Storage storage) throws DukeException {
        Task task = tasks.getTask(taskNumber);
        String successMessage = tasks.markTaskNotDone(taskNumber);
        return new Response<>(ResponseType.TASK, successMessage, task);
    }
}
