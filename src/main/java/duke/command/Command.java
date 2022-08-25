package duke.command;

import duke.exception.TedException;
import duke.storage.Storage;
import duke.task.*;
import duke.ui.Ui;

public abstract class Command {
    public abstract void execute(TaskList tasks, Ui ui, Storage st) throws TedException;
    public abstract boolean isExit();
}
