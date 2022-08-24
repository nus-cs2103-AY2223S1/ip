package duke.command;

import duke.task.TaskList;
import duke.ui.Ui;
import duke.storage.Storage;
import duke.DukeException;

public abstract class Command {
    public abstract void exec(TaskList tasks, Ui ui, Storage storage) throws DukeException;
    public abstract boolean isTerminator();
}
