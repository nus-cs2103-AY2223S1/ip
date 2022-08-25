package ted.command;

import ted.exception.TedException;
import ted.storage.Storage;
import ted.task.*;
import ted.ui.Ui;

public abstract class Command {
    public abstract void execute(TaskList tasks, Ui ui, Storage st) throws TedException;
    public abstract boolean isExit();
}
