package duke.command;

import java.util.function.Consumer;

import duke.task.Task;
import duke.task.TaskList;
import duke.util.Storage;

/**
 * Encapsulates a command to check or uncheck a {@code Task} from a {@code TaskList}.
 */
public class UpdateStatusCommand extends Command {

    private final int index;
    private final boolean isDone;

    /**
     * Constructs a new {@code UpdateStatusCommand} with the given {@code index} and {@code isDone}.
     *
     * @param index The index of the {@code Task} to update. Assumed 1-indexed.
     * @param isDone Whether the {@code Task} should be checked or unchecked.
     */
    public UpdateStatusCommand(int index, boolean isDone) {
        this.index = index;
        this.isDone = isDone;
    }

    /**
     * Updates the {@code Task} at the given {@code index} in the {@code TaskList} according to {@code isDone}.
     *
     * @param storage The {@code Storage} to use.
     * @param printer The {@code Consumer<String>} to use for printing.
     * @param tasks The {@code TaskList} to use.
     */
    @Override
    public void execute(Storage storage, Consumer<String> printer, TaskList tasks) {
        Task task = tasks.get(index - 1);
        task.setDone(isDone);
        storage.save(tasks);
        printer.accept("I've updated the status of this task\n\t" + task);
    }

    /**
     * Checks if an {@code Object} is same as this {@code UpdateStatusCommand}.
     *
     * @param o The {@code Object} to check.
     * @return {@code true} if the {@code Object} is same as this {@code UpdateStatusCommand}, {@code false} otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (o instanceof UpdateStatusCommand) {
            UpdateStatusCommand other = (UpdateStatusCommand) o;
            return this.index == other.index && this.isDone == other.isDone;
        }
        return false;
    }
}
