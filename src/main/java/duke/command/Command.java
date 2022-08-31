package duke.command;

import duke.task.TaskList;
import duke.util.Storage;

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
     * @param tasks The {@code TaskList} to use.
     */
    public abstract String execute(Storage storage, TaskList tasks);
}


