package roger.commands;

import roger.Storage;
import roger.TaskList;
import roger.Ui;

public abstract class Command {
    protected Command() {}

    public abstract void execute(TaskList tasks, Ui ui, Storage storage);

    public boolean isExit() {
        return false;
    }
}
