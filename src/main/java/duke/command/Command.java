package duke.command;

import duke.DukeException;
import duke.util.TaskList;
import duke.util.Storage;
import duke.util.Ui;

public abstract class Command {

    Storage storage;
    Ui ui;
    TaskList taskList;

    public Command(Storage storage, Ui ui, TaskList taskList) {
        this.storage = storage;
        this.ui = ui;
        this.taskList = taskList;
    }

    public boolean isExit() {
        return false;
    }

    public abstract void execute() throws DukeException;
}
