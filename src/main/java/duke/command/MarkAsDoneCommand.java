package duke.command;
import duke.Storage;
import duke.TaskList;
import duke.UI;
import duke.DukeException;
import duke.Task;

/**
 * MarkAsDoneCommand class to represent an instruction to mark a task as done.
 *
 * @author Sheryl Kong (A0240686Y)
 */

public class MarkAsDoneCommand extends Command {
    int[] taskNos;

    /**
     * Constructor for MarkAsDoneCommand class
     *
     * @param taskNos array of int
     */

    public MarkAsDoneCommand(int ... taskNos) {

        this.taskNos = taskNos;
    }

    @Override
    public void execute(TaskList taskList, Storage storage) throws DukeException {
        for (int taskNo : taskNos) {
            Task task = taskList.getTask(taskNo);
            task.markAsDone();
        }
        UI.markAsDone(taskNos, taskList);
        this.response = UI.markAsDoneResponse(taskNos, taskList);
        storage.saveData(taskList);
    }
}
