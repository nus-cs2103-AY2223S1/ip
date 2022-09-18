package duke.command;

import java.util.function.Consumer;

import duke.storage.Storage;
import duke.task.TaskList;

/**
 * Encapsulates a command that does nothing.
 */
public class EmptyCommand extends Command {

    /**
     * Does nothing for the execution of the command.
     *
     * @param storage The {@code Storage} to use.
     * @param printer The {@code Consumer<String>} to use for printing.
     * @param tasks The {@code TaskList} to use.
     */
    @Override
    public void execute(Storage storage, Consumer<String> printer, TaskList tasks) {
        // Does nothing
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
