package duke.command;
import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

public class MarkCommand extends Command {
    private int index;

    public MarkCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(Ui ui, Storage storage, TaskList taskList) throws DukeException {
        Task task = taskList.getTask(index);
        task.markAsDone();
        System.out.println("\t" + " Nice! I've marked this task as done:");
        System.out.println("\t " + task);
    }

    @Override
    public boolean isRunning() {
        return true;
    }
}
