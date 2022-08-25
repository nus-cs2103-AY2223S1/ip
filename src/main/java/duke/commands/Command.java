package duke.commands;

import duke.storage.Storage;
import duke.task.List;
import duke.ui.Ui;

/**
 * Represents an executable command.
 */
public abstract class Command {
    public abstract void execute(List tasks, Ui ui, Storage storage);

    public abstract boolean isExit();
}
