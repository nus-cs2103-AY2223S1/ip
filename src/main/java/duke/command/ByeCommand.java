package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

import java.io.IOException;

public class ByeCommand extends Command {
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws IOException {
        storage.save(tasks);
        ui.displayBye();
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
