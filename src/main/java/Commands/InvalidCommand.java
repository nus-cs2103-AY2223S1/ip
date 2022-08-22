package Commands;

import Duke.*;

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
