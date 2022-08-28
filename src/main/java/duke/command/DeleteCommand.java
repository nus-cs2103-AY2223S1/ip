package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.UI;
import duke.DukeException;
import duke.Task;

/**
 * DeleteCommand class to represent an instruction to delete a task from the TaskList.
 *
 * @author Sheryl Kong (A0240686Y)
 */

public class DeleteCommand extends Command {
    int taskNo;

    public DeleteCommand(int taskNo) {
        this.taskNo = taskNo;
    }

    @Override
    public void execute(TaskList taskList, Storage storage) throws DukeException {
        Task task = taskList.getTask(taskNo);
        taskList.delete(taskNo);
        Task.minusTaskCount();
        UI.delete(task);
    }
}
