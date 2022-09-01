package duke.command;

import duke.Response;
import duke.exception.DukeException;
import duke.task.Task;
import duke.task.TaskList;


/**
 * DeleteCommand represents a command to delete a Task from a TaskList.
 */
public class DeleteCommand extends Command {
    private TaskList taskList;
    private String[] inputArr;

    /**
     * Creates a DeleteCommand to delete a Task from a TaskList.
     *
     * @param taskList The TaskList to delete the Task from.
     * @param inputArr The input String array.
     */
    public DeleteCommand(TaskList taskList, String[] inputArr) {
        this.taskList = taskList;
        this.inputArr = inputArr;
    }

    /**
     * Deletes the Task from the TaskList.
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
            Task task = this.taskList.deleteTask(index);
            return new Response("Noted. I've removed this task:\n"
                    + task + "\n"
                    + "Now you have " + this.taskList.getSize() + " tasks in the list.\n");
        } catch (NumberFormatException exception) {
            throw new DukeException("Invalid task number.");
        } catch (DukeException exception) {
            throw exception;
        }
    }
}
