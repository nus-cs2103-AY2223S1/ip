package duke.command;

import java.io.IOException;

import duke.internal.MessageBuilder;
import duke.internal.Parser;
import duke.internal.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.task.Todo;

/**
 * A command to add a to-do task to the task list.
 * Usage: todo [0]
 * [0]: description
 */
public class TodoCommand extends Command {
    private final String description;

    /**
     * Constructs a command to add a to-do task to the task list.
     *
     * @param description the description of the to-do task
     * @throws IllegalArgumentException if the description contains a pipe character `|`
     */
    public TodoCommand(String description) {
        if (description.contains("|")) {
            throw new IllegalArgumentException("Description cannot contain the character '|'.");
        }
        this.description = description;
    }

    @Override
    public boolean isTerminal() {
        return false;
    }

    @Override
    public void execute(TaskList tasks, Storage storage, MessageBuilder messageBuilder,
                        Parser parser) throws IOException {
        Task task = new Todo(description);
        tasks.addTask(task);
        messageBuilder.addLine("I've added your task.")
                .addLine(task.toString())
                .addTaskListSize(tasks);
        storage.saveTasks(tasks);
    }
}
