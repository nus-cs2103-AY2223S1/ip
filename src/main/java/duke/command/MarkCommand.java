package duke.command;

import duke.Response;
import duke.exception.DukeException;
import duke.task.Task;
import duke.task.TaskList;

/**
 * MarkCommand represents a command to mark a task as completed.
 */
public class MarkCommand extends Command {
    private TaskList taskList;
    private String[] inputArr;

    /**
     * Creates a MarkCommand to mark a task as completed.
     *
     * @param taskList The TaskList that the task belongs to.
     * @param inputArr The input String array.
     */
    public MarkCommand(TaskList taskList, String[] inputArr) {
        this.taskList = taskList;
        this.inputArr = inputArr;
    }

    /**
     * Marks the task in the TaskList as completed.
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
            Task task = this.taskList.markDone(index);
            return new Response("Nice! I've marked this task as done:\n"
                    + task + "\n");
        } catch (NumberFormatException exception) {
            throw new DukeException("Invalid task number.");
        } catch (DukeException exception) {
            throw exception;
        }
    }
}
