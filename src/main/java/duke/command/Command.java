package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.exception.InvalidInputException;

public abstract class Command {
    private boolean isExit;

    public Command() {
        this.isExit = false;
    }

    public void setExitToTrue() {
        this.isExit = true;
    }

    public boolean isExit() {
        return this.isExit;
    }
    public abstract void run(TaskList tasks, Ui ui, Storage storage) throws InvalidInputException;
}
