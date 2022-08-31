package duke.command;

import java.util.function.Consumer;

import duke.task.TaskList;
import duke.util.Storage;

/**
 * Encapsulates a command that ends interaction.
 */
public class ExitCommand extends Command {

    /**
     * Returns true, since this is a terminal command.
     *
     * @return true
     */
    @Override
    public boolean isExit() {
        return true;
    }

    /**
     * Calls the {@code UI} to exit interaction.
     *
     * @param storage The {@code Storage} to use.
     * @param printer The {@code Consumer<String>} to use for printing.
     * @param tasks The {@code TaskList} to use.
     */
    @Override
    public void execute(Storage storage, Consumer<String> printer, TaskList tasks) {
        printer.accept("Bye! Hope to see you soon.");
    }

    /**
     * Checks if an {@code Object} is same as this {@code ExitCommand}.
     *
     * @param o The {@code Object} to check.
     * @return {@code true} if the {@code Object} is same as this {@code ExitCommand}, {@code false} otherwise.
     */
    @Override
    public boolean equals(Object o) {
        return o instanceof ExitCommand;
    }
}
