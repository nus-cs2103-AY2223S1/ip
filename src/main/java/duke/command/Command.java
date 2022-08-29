package duke.command;

import duke.DukeException;
import duke.operations.Storage;
import duke.operations.TaskList;
import duke.operations.Ui;

public abstract class Command {

    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException;

    public boolean isExit() {
        return false;
    }
}
