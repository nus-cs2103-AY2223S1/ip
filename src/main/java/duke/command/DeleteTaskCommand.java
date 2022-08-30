package duke.command;

import duke.DukeException;
import duke.task.TaskList;
import duke.util.Storage;
import duke.util.Ui;

/**
 * Encapsulates a command to delete a {@code Task} from a {@code TaskList}.
 */
public class DeleteTaskCommand extends Command {

    private final int index;

    /**
     * Constructs a new {@code DeleteTaskCommand} with the given {@code index}.
     *
     * @param index The index of the {@code Task} to delete. Assumed 1-indexed.
     */
    public DeleteTaskCommand(int index) {
        this.index = index;
    }

    /**
     * Deletes the {@code Task} at the given {@code index} from the {@code TaskList}.
     *
     * @param storage The {@code Storage} to use.
     * @param ui The {@code UI} to use.
     * @param tasks The {@code TaskList} to use.
     */
    @Override
    public void execute(Storage storage, Ui ui, TaskList tasks) throws DukeException {
        ui.print("I'm removing the following task:\n\t" + tasks.get(index - 1));
        tasks.remove(index - 1);
        storage.save(tasks);
    }

    /**
     * Checks if an {@code Object} is same as this {@code DeleteTaskCommand}.
     *
     * @param o The {@code Object} to check.
     * @return {@code true} if the {@code Object} is same as this {@code DeleteTaskCommand}, {@code false} otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (o instanceof DeleteTaskCommand) {
            DeleteTaskCommand other = (DeleteTaskCommand) o;
            return this.index == other.index;
        }
        return false;
    }
}
