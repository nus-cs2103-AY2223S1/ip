package duke.command;

import duke.operations.Storage;
import duke.operations.TaskList;
import duke.operations.Ui;

public class ExitCommand extends Command {
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showExit();
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
