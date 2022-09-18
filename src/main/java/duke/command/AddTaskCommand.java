package duke.command;

import java.util.function.Consumer;

import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;

/**
 * Encapsulates a command to add a {@code Task} to a {@code TaskList}.
 */
public class AddTaskCommand extends Command {

    public static final String HELP_STRING = "- todo <description>:\n"
            + "Adds a todo task to the task list with the given description.\n\n"
            + "- deadline <description> / <date-time>:\n"
            + "Adds a deadline task to the task list with the given description and date.\n"
            + "The date-time should be in the format dd-mm-yy HHmm.\n\n"
            + "- event <description> / <date-time>:\n"
            + "Adds an event task to the task list with the given description and date.\n"
            + "The date-time should be in the format dd-mm-yy HHmm.";
    private static final String ADD_TASK_RESPONSE_FORMAT = "I've added the following task:\n\t%s";

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
     * @param printer The {@code Consumer<String>} to use for printing.
     * @param tasks The {@code TaskList} to use.
     */
    @Override
    public void execute(Storage storage, Consumer<String> printer, TaskList tasks) {
        tasks.add(task);
        storage.save(tasks);
        printer.accept(String.format(ADD_TASK_RESPONSE_FORMAT, task));
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
