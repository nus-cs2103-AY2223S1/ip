package Commands;

import Duke.Storage;
import Duke.TaskList;
import Duke.Ui;

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
