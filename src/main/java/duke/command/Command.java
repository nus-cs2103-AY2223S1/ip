package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.UI;

public abstract class Command {
    private final boolean isBye;

    public Command(boolean isBye) {
        this.isBye = isBye;
    }

    public boolean isBye() {
        return isBye;
    }

    public abstract void execute(TaskList taskList, UI ui, Storage storage) throws DukeException;
}
