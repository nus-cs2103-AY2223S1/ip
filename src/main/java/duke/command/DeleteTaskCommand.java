package duke.command;

import java.util.function.Consumer;

import duke.DukeException;
import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;

/**
 * Encapsulates a command to delete a {@code Task} from a {@code TaskList}.
 */
public class DeleteTaskCommand extends Command {

    public static final String HELP_STRING = "- delete <index>:\n"
            + "Deletes the task at the given index from the task list.";
    private static final String DELETE_RESPONSE_FORMAT = "I've deleted the following task:\n\t%s";

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
     * @param printer The {@code Consumer<String>} to use for printing.
     * @param tasks The {@code TaskList} to use.
     */
    @Override
    public void execute(Storage storage, Consumer<String> printer, TaskList tasks) {
        if (index < 1 || index > tasks.size()) {
            throw new DukeException("Invalid task index.");
        }
        Task task = tasks.get(index - 1);
        tasks.remove(index - 1);
        storage.save(tasks);
        printer.accept(String.format(DELETE_RESPONSE_FORMAT, task));
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
