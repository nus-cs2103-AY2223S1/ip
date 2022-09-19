package duke.command;
import duke.*;

/**
 * MarkAsUndoneCommand class to represent an instruction to mark a task as undone.
 *
 * @author Sheryl Kong (A0240686Y)
 */

public class MarkAsUndoneCommand extends Command {
    int[] taskNos;

    /**
     * Constructor for MarkAsUndoneCommand class
     *
     * @param taskNos array of int
     */

    public MarkAsUndoneCommand(int ... taskNos) {
        this.taskNos = taskNos;
    }

    @Override
    public void execute(TaskList taskList, Storage storage) throws DukeException {
        for (int taskNo : taskNos) {
            Task task = taskList.getTask(taskNo);
            task.markAsUndone();
        }
        UI.markAsUndone(taskNos, taskList);
        response = UI.markAsUndoneResponse(taskNos, taskList);
        storage.saveData(taskList);
    }
}
