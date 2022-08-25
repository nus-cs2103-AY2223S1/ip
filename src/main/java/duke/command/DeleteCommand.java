package duke.command;
import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

public class DeleteCommand extends Command {
    private int index;

    public DeleteCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(Ui ui, Storage storage, TaskList tasklist) throws DukeException {
        Task task = tasklist.getTask(this.index);
        tasklist.remove(this.index - 1);
        int numTasks = tasklist.size();
        ui.formatMessage("Noted. I've removed this task:");
        ui.formatMessage(task.toString());
        if (tasklist.size() == 1) {
            ui.formatMessage(String.format("Now you have %d task in the list.", tasklist.size()));
        } else {
            ui.formatMessage(String.format("Now you have %d tasks in the list.", tasklist.size()));
        }
    }

    @Override
    public boolean isRunning() {
        return true;
    }
}
