package duke.handlers;

import duke.exceptions.DukeException;
import duke.models.DukeResponse;
import duke.models.TaskList;

public class MarkAsUndoneCommand implements DukeCommand {
    public DukeResponse run (TaskList taskList, String content) throws DukeException {
        try {
            int taskNum = Integer.parseInt(content);
            taskList.get(taskNum - 1).markAsUndone();
            return new DukeResponse("This task is not done yet:\n" + taskList.get(taskNum - 1));
        } catch (NumberFormatException e) {
            throw new DukeException("Task index is not a number!\n");
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("Task index out of bounds!");
        }
    }
}