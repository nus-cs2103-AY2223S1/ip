package tako.command;

import java.io.IOException;

import tako.Storage;
import tako.TaskList;
import tako.Ui;

public class ListCommand extends Command {
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws IOException {
        ui.showList(tasks);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
