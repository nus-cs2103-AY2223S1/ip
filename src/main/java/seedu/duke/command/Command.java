package seedu.duke.command;

import seedu.duke.storage.Storage;
import seedu.duke.task.TaskList;
import seedu.duke.ui.Ui;

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
