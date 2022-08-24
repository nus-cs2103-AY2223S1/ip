package duke.commands;

import duke.data.TaskList;
import duke.data.exception.DukeException;
import duke.storage.Storage;
import duke.ui.Ui;

public abstract class Command {
    public abstract boolean isBye();

    public abstract void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException;
}
