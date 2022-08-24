package command;

import storage.Storage;
import task.TaskList;
import ui.Ui;

public abstract class Command {
    protected boolean isExit;

    public Command(boolean isExit) {
        this.isExit = isExit;
    }
    abstract public void execute(TaskList tasks, Ui ui, Storage storage);
    public boolean isExit() {
        return this.isExit;
    }
}
