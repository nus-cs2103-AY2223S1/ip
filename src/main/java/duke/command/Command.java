package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.ui.Ui;

public interface Command {
    public abstract void execute(TaskList itemList, Ui ui, Storage storage)
            throws DukeException;
}
