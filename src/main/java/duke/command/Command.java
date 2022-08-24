package duke.command;
import duke.exception.DukeException;
import duke.task.Task;
import duke.TaskList;
import duke.Ui;
import duke.Storage;
public abstract class Command {
    public Task task;
    public Command(Task task) {
        this.task = task;
    }
    public Command() {
    }
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException;

    public boolean isExit() {
        return false;
    }

}
