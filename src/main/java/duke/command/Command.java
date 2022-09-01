package duke.command;

import duke.command.Command;
import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

public abstract class Command {
    protected boolean isExit;

    Command() {
        isExit = false;
    }

    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        throw new DukeException("Not implemented");
    }

    public boolean isExit() {
        return isExit;
    }
}
