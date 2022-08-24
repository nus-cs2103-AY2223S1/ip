package duke.command;
import duke.exception.DukeException;
import duke.task.Task;
import duke.TaskList;
import duke.Ui;
import duke.Storage;
public class ExitCommand extends Command{
    public ExitCommand() {
        super();
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
