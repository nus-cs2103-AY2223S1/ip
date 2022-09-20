package duke.handlers;

import duke.exceptions.DukeException;
import duke.models.DukeResponse;
import duke.models.TaskList;

/**
 * Represents a command that marks a task as done.
 */
public class MarkAsDoneCommand implements DukeCommand {

    /**
     * Mark a task as done.
     *
     * @param taskList The tasklist that contains the task to be marked as done.
     * @param content The user input specifying which task to be marked.
     * @return The DukeResponse that contains the message about the marked task.
     * @throws DukeException If the task number user input is not a number or out
     *                       of bounds.
     */
    @Override
    public DukeResponse run (TaskList taskList, String content) throws DukeException {
        try {
            int taskNum = Integer.parseInt(content);
            taskList.get(taskNum - 1).markAsDone(taskList); // since taskList is 0-indexed
            return new DukeResponse("Good job on completing the task:\n" + taskList.get(taskNum - 1));
        } catch (NumberFormatException e) {
            throw new DukeException("Task index is not a number!\n");
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("Task index out of bounds!\n");
        }
    }
}
