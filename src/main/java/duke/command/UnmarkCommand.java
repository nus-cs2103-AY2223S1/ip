package duke.command;

import duke.Response;
import duke.exception.DukeException;
import duke.task.Task;
import duke.task.TaskList;

/**
 * UnmarkCommand represents a command to mark a task as uncompleted.
 */
public class UnmarkCommand extends Command {
    private TaskList taskList;
    private String[] inputArr;

    /**
     * Creates a UnmarkCommand to mark a task as uncompleted.
     *
     * @param taskList The TaskList that the task belongs to.
     * @param inputArr The input String array.
     */
    public UnmarkCommand(TaskList taskList, String[] inputArr) {
        this.taskList = taskList;
        this.inputArr = inputArr;
    }

    /**
     * Marks the task in the TaskList as uncompleted.
     *
     * @return The Response to be displayed.
     * @throws DukeException If the input array is invalid.
     */
    @Override
    public Response execute() throws DukeException {
        if (this.inputArr.length < 2) {
            throw new DukeException("Missing task number.");
        }
        try {
            int index = Integer.parseInt(this.inputArr[1]) - 1;
            Task task = this.taskList.unmarkDone(index);
            return new Response("OK, I've marked this task as not done yet:\n"
                    + task + "\n");
        } catch (NumberFormatException exception) {
            throw new DukeException("Invalid task number.");
        }
    }
}
