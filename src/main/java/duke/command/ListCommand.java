package duke.command;

import duke.storage.Storage;
import duke.task.*;
import duke.ui.Ui;

public class ListCommand extends Command {
    @Override
    public void execute(TaskList tasks, Ui ui, Storage st) {
        ui.tedResponse(tasks.list());
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
