package duke.command;

import duke.data.Storage;
import duke.task.*;
import duke.ui.Ui;

public class ExitCommand extends Command {

    @Override
    public boolean isExit() {
        return true;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        ui.showExitMessage();
    }
}
