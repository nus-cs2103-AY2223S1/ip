package drake.commands;

import drake.DrakeException;
import drake.Storage;
import drake.TaskList;
import drake.Ui;

import java.io.IOException;

public class ByeCommand extends Command {
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws IOException, DrakeException {
        ui.printBye();
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
