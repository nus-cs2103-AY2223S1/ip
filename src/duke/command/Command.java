package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

public abstract class Command {
    public abstract void execute(TaskList tasks, Storage storage, Ui ui) throws DukeException;

    public abstract boolean bye();
}
