package yilia.command;

import yilia.Storage;
import yilia.Ui;
import yilia.exception.DescriptionEmptyException;
import yilia.exception.TimeFormatException;
import yilia.task.TaskList;

public abstract class Command {
    private boolean isExit = false;
    public Command() {

    }
    public Command(boolean isExit) {
        this.isExit = isExit;
    }
    public boolean isExit() {
        return isExit;
    }
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws
            DescriptionEmptyException, TimeFormatException;
}
