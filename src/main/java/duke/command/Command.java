package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.exception.DukeException;

import java.io.IOException;

public abstract class Command {
    protected boolean isExit;

    public Command() {
        this.isExit = false;
    }

    public boolean isExit() {
        return this.isExit;
    }

    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException, IOException;
}
