package duke.command;

import duke.utils.Storage;
import duke.utils.TaskList;
import duke.utils.UI;
import duke.exception.DukeException;

public abstract class Command {
    Storage storage;
    UI ui;
    TaskList taskList;

    public Command(Storage storage, UI ui, TaskList taskList) {
        this.storage = storage;
        this.ui = ui;
        this.taskList = taskList;
    }

    public Command() {

    }

    public abstract void execute(Storage storage, UI ui, TaskList taskList) throws DukeException;
}
