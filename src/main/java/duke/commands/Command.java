package duke.commands;

import duke.exceptions.DukeException;
import duke.utils.Storage;
import duke.utils.TaskList;
import duke.utils.Ui;

public abstract class Command {
    public boolean isExit() {
        return false;
    }

    public abstract void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException;
}
