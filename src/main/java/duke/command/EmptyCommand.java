package duke.command;

import duke.task.TaskList;
import duke.util.Storage;

/**
 * Encapsulates a command that does nothing.
 */
public class EmptyCommand extends Command {

    /**
     * Does nothing for the execution of the command.
     *
     * @param storage The {@code Storage} to use.
     * @param tasks The {@code TaskList} to use.
     */
    @Override
    public String execute(Storage storage, TaskList tasks) {
        // does nothing
        return "";
    }

    /**
     * Checks if an {@code Object} is same as this {@code EmptyCommand}.
     *
     * @param o The {@code Object} to check.
     * @return {@code true} if the {@code Object} is same as this {@code EmptyCommand}, {@code false} otherwise.
     */
    @Override
    public boolean equals(Object o) {
        return o instanceof EmptyCommand;
    }
}
