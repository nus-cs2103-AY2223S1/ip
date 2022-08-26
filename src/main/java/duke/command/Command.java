package duke.command;

import duke.data.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

abstract public class Command {

    protected boolean isExit = false;

    public abstract void execute(Storage storage, TaskList tasks, Ui ui);

    public boolean isExit() {
        return isExit;
    }
}
