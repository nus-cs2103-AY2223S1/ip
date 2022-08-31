package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

public class UnknownCommand extends Command {
    @Override
    public void execute(TaskList tasks, Storage storage, Ui ui) throws DukeException {
        ui.showUnknownCommand();
    }

    @Override
    public boolean bye() {
        return false;
    }
}
