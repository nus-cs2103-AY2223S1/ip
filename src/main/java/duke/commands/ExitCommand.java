package duke.commands;

import duke.ui.Ui;
import duke.data.TaskList;
import duke.storage.Storage;

public class ExitCommand extends Command {

    @Override
    public boolean isExit() {
        return true;
    }

    @Override
    public void execute(Ui ui, Storage storage, TaskList taskList) {
        ui.printExit();
    }
}
