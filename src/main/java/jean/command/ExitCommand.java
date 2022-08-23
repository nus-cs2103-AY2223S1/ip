package jean.command;

import java.io.IOException;

import jean.storage.Storage;
import jean.task.TaskList;
import jean.ui.Ui;

public class ExitCommand extends Command {

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws IOException {
        storage.saveFile(taskList);
        ui.end();
    }
}
