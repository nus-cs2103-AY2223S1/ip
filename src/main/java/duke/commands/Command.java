package duke.commands;

import duke.DukeException;
import duke.storage.StorageFile;
import duke.task.TaskList;
import duke.ui.Ui;

public abstract class Command {
    public boolean isExit() {
        return this instanceof ByeCommand;
    }

    public abstract void execute(TaskList taskList, Ui ui, StorageFile storage) throws DukeException;

}
