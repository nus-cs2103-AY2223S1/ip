package duke.command;

import java.io.IOException;
import duke.Ui;
import duke.Storage;
import duke.TaskList;

public class ExitCommand extends Command {
    @Override
    public boolean isExit() {
        return true;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws IOException {
        storage.save(taskList.getTaskArrayList());
        ui.showGoodbye();
    }
}
