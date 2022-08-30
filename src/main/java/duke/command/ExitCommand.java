package duke.command;

import duke.task.TaskList;
import duke.util.Storage;
import duke.util.Ui;

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
     * @param ui The {@code UI} to use.
     * @param tasks The {@code TaskList} to use.
     */
    @Override
    public void execute(Storage storage, Ui ui, TaskList tasks) {
        ui.exit();
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
