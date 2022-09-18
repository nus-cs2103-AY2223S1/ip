package duke.handlers;

import duke.exceptions.DukeException;
import duke.models.DukeResponse;
import duke.models.TaskList;

public class DeleteTaskCommand implements DukeCommand {
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
