package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

public class ListCommand extends Command {
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.listMessage(tasks);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
