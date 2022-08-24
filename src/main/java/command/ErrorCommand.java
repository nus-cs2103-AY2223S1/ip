package command;
import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;
public class ErrorCommand extends Command {

    String error;

    public ErrorCommand(String error) {
        this.error = error;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        throw new DukeException(error);
    }
}
