package commands;

import data.*;
import data.exception.DukeException;
import storage.Storage;
import ui.Ui;

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
