package command;

import common.DukeException;
import storage.Storage;
import task.TaskList;
import ui.Ui;

public class ExitCommand extends Command {
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        ui.showOutput("Bye!");
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
