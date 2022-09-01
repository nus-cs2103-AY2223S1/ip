package duke.command;

import java.util.function.Consumer;

import duke.task.Task;
import duke.task.TaskList;
import duke.util.Storage;

/**
 * Encapsulates a command to perform keyword saerch.
 */
public class FindCommand extends Command {

    private final String keyword;

    /**
     * Creates a new {@code FindCommand} with the given keyword.
     *
     * @param keyword The keyword to search for.
     */
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    /**
     * Searches the {@code TaskList} for the keyword.
     *
     * @param storage The {@code Storage} to use.
     * @param printer The {@code Consumer<String>} to use for printing.
     * @param tasks The {@code TaskList} to use.
     */
    @Override
    public void execute(Storage storage, Consumer<String> printer, TaskList tasks) {
        TaskList foundTasks = new TaskList();
        for (Task task : tasks) {
            if (task.contains(keyword)) {
                foundTasks.add(task);
            }
        }
        printer.accept("Tasks containing \"" + keyword + "\":\n" + foundTasks);
    }

    /**
     * Checks if an {@code Object} is same as this {@code FindCommand}.
     * @param o The {@code Object} to check.
     * @return {@code true} if the {@code Object} is same as this {@code FindCommand}, {@code false} otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (o instanceof FindCommand) {
            FindCommand other = (FindCommand) o;
            return this.keyword.equals(other.keyword);
        }
        return false;
    }
}
