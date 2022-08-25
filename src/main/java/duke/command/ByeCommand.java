package duke.command;

import duke.storage.Storage;
import duke.task.*;
import duke.ui.Ui;

public class ByeCommand extends Command {
    @Override
    public void execute(TaskList tasks, Ui ui, Storage st) {
        ui.showExit();
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
