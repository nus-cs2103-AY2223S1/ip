package duke.command;

import duke.common.DukeException;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

public abstract class Command {
    public abstract void execute(Ui ui, Storage storage, TaskList taskList) throws DukeException;

    public abstract boolean isExit();
}
