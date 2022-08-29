package duke.command;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

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
