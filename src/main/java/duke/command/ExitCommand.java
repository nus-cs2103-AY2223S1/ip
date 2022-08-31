package duke.command;

import duke.common.DukeException;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

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
