package cheese.command;

import cheese.data.TaskList;
import cheese.exception.CheeseException;
import cheese.storage.Storage;
import cheese.ui.Ui;

public abstract class Command {
    public abstract void execute(TaskList taskList, Storage storage, Ui ui) throws CheeseException;
}
