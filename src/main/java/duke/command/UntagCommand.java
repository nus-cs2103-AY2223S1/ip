package duke.command;

import duke.Response;
import duke.exception.DukeException;
import duke.task.Task;
import duke.task.TaskList;

/**
 * UntagCommand represents a command to remove a tag from a task.
 */
public class UntagCommand extends Command {
    private TaskList taskList;
    private String[] inputArr;

    /**
     * Creates an UntagCommand to remove a tag from a task.
     *
     * @param taskList The TaskList that the task belongs to.
     * @param inputArr The input String array.
     */
    public UntagCommand(TaskList taskList, String[] inputArr) {
        this.taskList = taskList;
        this.inputArr = inputArr;
    }

    /**
     * Removes a tag from a task in the TaskList.
     *
     * @return The Response to be displayed.
     * @throws DukeException If the input array is invalid.
     */
    @Override
    public Response execute() throws DukeException {
        if (inputArr.length < 2) {
            throw new DukeException("Missing task number and tag.");
        }
        String[] indexTag = inputArr[1].split(" ", 2);
        if (indexTag.length < 2) {
            throw new DukeException("Missing task number or tag.");
        }
        try {
            int index = Integer.parseInt(indexTag[0]) - 1;
            Task task = taskList.untagTask(index, indexTag[1].split(" "));
            assert task != null : "Task cannot be null";
            return new Response("Ok! I've untagged this task:\n"
                    + task + "\n");
        } catch (NumberFormatException exception) {
            throw new DukeException("Invalid task number.");
        } catch (DukeException exception) {
            throw exception;
        }
    }
}
