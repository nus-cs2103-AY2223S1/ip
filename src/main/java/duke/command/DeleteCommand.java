package duke.command;

import duke.exception.DukeException;
import duke.manager.Storage;
import duke.manager.TaskList;

/**
 * Represents a command to delete a task from the list.
 */
public class DeleteCommand extends Command {
    /** The task number to be deleted. */
    private int taskNumber;

    public DeleteCommand(int taskNumber) {
        this.taskNumber = taskNumber;
    }

    @Override
    public String execute(TaskList tasks, Storage storage) throws DukeException {
        String task = tasks.deleteTask(taskNumber);
        String response = "DELETE TASK:\n " + task + "\nNow you have "
                + tasks.getLength() + " task(s) left!";

        storage.saveTasks(tasks);

        return response;
    }
}