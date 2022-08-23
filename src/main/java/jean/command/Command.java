package jean.command;

import java.io.IOException;

import jean.storage.Storage;
import jean.task.TaskList;
import jean.ui.Ui;

public abstract class Command {
    public abstract void execute(TaskList taskList, Ui ui, Storage storage) throws IOException;

    public boolean isExit() {
        return this instanceof ExitCommand;
    }
}
