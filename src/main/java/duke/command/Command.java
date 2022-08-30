package duke.command;

import duke.task.TaskList;
import duke.util.Storage;
import duke.util.Ui;

/**
 * Encapsulates a command.
 */
public abstract class Command {

    /**
     * Checks if this {@code Command} is a terminal command.
     *
     * @return {@code true} if this {@code Command} is a terminal command, {@code false} otherwise.
     */
    public boolean isExit() {
        return false;
    }

    /**
     * Executes the command.
     *
     * @param storage The {@code Storage} to use.
     * @param ui The {@code UI} to use.
     * @param tasks The {@code TaskList} to use.
     */
    public abstract void execute(Storage storage, Ui ui, TaskList tasks);
}


