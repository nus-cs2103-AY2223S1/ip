package duke.command;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Abstract class for commands.
 */
public abstract class Command {
    /**
     * Executes a command.
     * @param tasks
     * @param ui
     * @param storage
     */
    public abstract void execute(TaskList tasks, Ui ui, Storage storage);

    /**
     * Check if the command exit duke.
     * @return
     */
    public abstract boolean isExit();
}
