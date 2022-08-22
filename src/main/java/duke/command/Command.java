package duke.command;

import duke.DukeException;
import duke.task.TaskList;
import duke.util.Storage;
import duke.util.UI;

public abstract class Command {

    public boolean isExit() {
        return false;
    }

    public abstract void execute(Storage storage, UI ui, TaskList tasks) throws DukeException;
}


