package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.UI;
import duke.DukeException;
import duke.Task;

import java.util.Arrays;

/**
 * DeleteCommand class to represent an instruction to delete a task from the TaskList.
 *
 * @author Sheryl Kong (A0240686Y)
 */

public class DeleteCommand extends Command {
    private final int[] taskNos;

    public DeleteCommand(int ... taskNos) {
        this.taskNos = taskNos;
    }

    @Override
    public void execute(TaskList taskList, Storage storage) throws DukeException {
        UI.delete(taskNos, taskList);
        response = UI.deleteResponse(taskNos, taskList);
        Arrays.sort(taskNos);

        for (int i = taskNos.length-1; i >= 0; i--) {
            Task task = taskList.getTask(taskNos[i]);
            taskList.delete(task);
            Task.minusTaskCount();
        }
        storage.saveData(taskList);
    }
}
