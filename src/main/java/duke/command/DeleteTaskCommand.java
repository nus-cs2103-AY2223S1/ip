package duke.command;

import duke.Storage;
import duke.exception.DukeException;
import duke.task.TaskList;

/**
 * Represents a command that deletes a specified task from the specified task list.
 */
public class DeleteTaskCommand extends Command {
    private int taskNumber;

    /**
     * Constructs a DeleteTaskCommand.
     *
     * @param taskNumber The task number of the task in the task list to be deleted (1-indexed)
     */
    public DeleteTaskCommand(int taskNumber) {
        this.taskNumber = taskNumber;
    }

    /**
     * Deletes the task associated to the task number from the specified task list.
     *
     * @param tasks The task list the task is to be deleted to
     * @param storage The storage manager that deals with loading and saving tasks to the hard disk
     * @throws DukeException If the task is invalid (eg: does not exist, or is not a positive number)
     */
    @Override
    public String execute(TaskList tasks, Storage storage) throws DukeException {
        return tasks.deleteTask(this.taskNumber);
    }
}
