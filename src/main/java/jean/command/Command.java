package jean.command;

import jean.storage.Storage;
import jean.task.TaskList;
import jean.ui.Ui;

import java.io.IOException;

public abstract class Command {
    public abstract void execute(TaskList taskList, Ui ui, Storage storage) throws IOException;

    public boolean isExit() {
        return this instanceof ExitCommand;
    }
}
