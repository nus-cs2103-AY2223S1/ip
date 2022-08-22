package duke.commands;

import duke.ui.Ui;
import duke.data.TaskList;
import duke.data.exception.DukeException;
import duke.storage.Storage;

public class InvalidCommand extends Command {

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public void execute(Ui ui, Storage storage, TaskList taskList) throws DukeException {
        ui.printInvalid();
    }
}
