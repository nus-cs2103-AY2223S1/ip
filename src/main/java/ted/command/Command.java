package ted.command;

import ted.Storage;
import ted.task.TaskList;
import ted.exception.TedException;
import ted.Ui;

public abstract class Command {

    protected String args;

    public Command(String args) {
        this.args = args;
    }

    abstract public void run(TaskList tasks, Ui ui, Storage storage) throws TedException;

    public boolean isExit() {
        return false;
    }
}
