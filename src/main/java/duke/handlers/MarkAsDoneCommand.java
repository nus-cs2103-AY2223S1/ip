package duke.handlers;

import duke.exceptions.DukeException;
import duke.models.DukeResponse;
import duke.models.TaskList;

public class MarkAsDoneCommand implements DukeCommand {
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
