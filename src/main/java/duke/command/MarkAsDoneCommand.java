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
    int taskNo;

    public MarkAsDoneCommand(int taskNo) {
        this.taskNo = taskNo;
    }

    @Override
    public void execute(TaskList taskList, Storage storage) throws DukeException {
        Task task = taskList.getTask(taskNo);
        task.markAsDone();
        UI.markAsDone(task);
    }
}
