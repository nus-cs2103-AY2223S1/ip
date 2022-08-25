package duke.commands;

import duke.storage.Storage;
import duke.task.List;
import duke.ui.Ui;

/**
 * Represents an executable command.
 */
public abstract class Command {
    /**
     * Executes the command and returns the result.
     */
    public abstract void execute(List tasks, Ui ui, Storage storage);

    /**
     * Returns true if the command is an exit command.
     */
    public abstract boolean isExit();
}
