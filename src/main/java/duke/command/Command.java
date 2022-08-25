package duke.command;

import duke.TaskList;
import duke.Ui;
import duke.Storage;
import duke.DukeException;

public abstract class Command {
    public boolean isExit() {
        return false;
    }
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException;
}
