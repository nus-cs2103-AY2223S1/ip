package duke.commands;

import duke.data.TaskList;
import duke.data.exception.DukeException;
import duke.storage.Storage;
import duke.ui.Ui;

public abstract class Command {
    private boolean isExit = false;

    public abstract boolean isExit();

    public abstract void execute(Ui ui, Storage storage, TaskList taskList) throws DukeException;
}
