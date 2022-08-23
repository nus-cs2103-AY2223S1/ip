package duke.command;

import duke.task.*;
import duke.ui.Ui;
import duke.data.Storage;

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
