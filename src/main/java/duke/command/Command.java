package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

public abstract class Command {
    public abstract void execute(TaskList list, Ui ui, Storage storage);

    protected boolean isExit = false;

    public boolean isExit() {
        return this.isExit;
    }
}
