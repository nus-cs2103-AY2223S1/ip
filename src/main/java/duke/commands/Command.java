package duke.commands;

import duke.ui.Ui;
import duke.data.TaskList;
import duke.data.exception.DukeException;
import duke.storage.Storage;

public abstract class Command {
    boolean isExit = false;

    public abstract boolean isExit();

    public abstract void execute(Ui ui, Storage storage, TaskList taskList) throws DukeException;
}
