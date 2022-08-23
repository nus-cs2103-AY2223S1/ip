package roger.commands;

import roger.Storage;
import roger.TaskList;
import roger.Ui;

public class ExitCommand extends Command {

    public void execute(TaskList tasks, Ui ui, Storage storage)  {
        ui.showFarewell();
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
