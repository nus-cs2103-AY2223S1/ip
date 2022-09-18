package duke.handlers;

import duke.exceptions.DukeException;
import duke.models.DukeResponse;
import duke.models.TaskList;

/**
 * Represents a command to delete a task.
 */
public class DeleteTaskCommand implements DukeCommand {

    /**
     * Deletes the specified task.
     *
     * @param taskList The tasklist from which the task will be deteled.
     * @param content The user input specifying the task number to delete.
     * @return The response containing message about the deleted task.
     * @throws DukeException If the task number user input is not a number or out
     *                       of bounds.
     */
    public DukeResponse run (TaskList taskList, String content) throws DukeException {
        try {
            int taskNum = Integer.parseInt(content);
            return new DukeResponse("Task deleted:\n" + taskList.deleteTask(taskNum - 1));
        } catch (NumberFormatException e) {
            throw new DukeException("Task index is not a number!\n");
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("Task index out of bounds!\n");
        }
    }
}
