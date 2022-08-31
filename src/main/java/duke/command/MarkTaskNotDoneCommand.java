package duke.command;

import duke.Storage;
import duke.exception.DukeException;
import duke.task.TaskList;

/**
 * Represents a command that marks a specified task as not done.
 */
public class MarkTaskNotDoneCommand extends Command {
    private int taskNumber;

    /**
     * Constructs a MarkTaskNotDOneCommand.
     *
     * @param taskNumber The task number of the completed task
     */
    public MarkTaskNotDoneCommand(int taskNumber) {
        this.taskNumber = taskNumber;
    }

    /**
     * Executes the MarkTaskNotDoneCommand, marks the task with the associated task
     * number in the specified task list as not done.
     *
     * @param tasks The task list which the task belongs to
     * @param storage The storage manager that deals with loading and saving tasks to the hard disk
     * @throws DukeException If the task number is invalid (eg: Task does not exist/ task number is not positive)
     */
    @Override
    public String execute(TaskList tasks, Storage storage) throws DukeException {
        return tasks.markTaskNotDone(this.taskNumber);
    }
}
