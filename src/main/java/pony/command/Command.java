package pony.command;

import pony.Storage;
import pony.task.TaskList;
import pony.Ui;

public abstract class Command {

    protected boolean isExit = false;

    public abstract void execute(TaskList tasks, Storage storage, Ui ui);
    public boolean isExit() {
        return this.isExit;
    }
}
