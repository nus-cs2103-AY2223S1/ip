package duke.command;

import duke.task.Task;
import duke.task.TaskList;
import duke.util.Storage;

/**
 * Encapsulates a command to add a {@code Task} to a {@code TaskList}.
 */
public class AddTaskCommand extends Command {

    private final Task task;

    /**
     * Creates a new {@code AddTaskCommand} with the given {@code Task}.
     *
     * @param task The {@code Task} to add.
     */
    public AddTaskCommand(Task task) {
        this.task = task;
    }

    /**
     * Adds the task to the task list.
     *
     * @param storage The {@code Storage} to use.
     * @param tasks The {@code TaskList} to use.
     */
    @Override
    public String execute(Storage storage, TaskList tasks) {
        tasks.add(task);
        storage.save(tasks);
        return "I've added the following task:\n\t" + task;
    }

    /**
     * Checks if an {@code Object} is same as this {@code AddTaskCommand}.
     *
     * @param o The {@code Object} to check.
     * @return {@code true} if the {@code Object} is same as this {@code AddTaskCommand}, {@code false} otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (o instanceof AddTaskCommand) {
            AddTaskCommand other = (AddTaskCommand) o;
            return this.task.equals(other.task);
        }
        return false;
    }
}
