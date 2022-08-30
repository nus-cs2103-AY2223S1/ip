package duke.command;

import duke.task.TaskList;
import duke.util.Storage;
import duke.util.Ui;

/**
 * Encapsulates a command to list {@code Task}s in a {@code TaskList}.
 */
public class ListCommand extends Command {

    /**
     * Prints the {@code Task}s in the {@code TaskList} to the {@code UI}.
     *
     * @param storage The {@code Storage} to use.
     * @param ui The {@code UI} to use.
     * @param tasks The {@code TaskList} to use.
     */
    @Override
    public void execute(Storage storage, Ui ui, TaskList tasks) {
        ui.print("List of tasks:\n" + tasks);
    }

    /**
     * Checks if an {@code Object} is same as this {@code ListCommand}.
     *
     * @param o The {@code Object} to check.
     * @return {@code true} if the {@code Object} is same as this {@code ListCommand}, {@code false} otherwise.
     */
    @Override
    public boolean equals(Object o) {
        return o instanceof ListCommand;
    }
}

