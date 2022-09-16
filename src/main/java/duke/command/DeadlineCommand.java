package duke.command;

import java.io.IOException;

import duke.internal.DukeException;
import duke.internal.MessageBuilder;
import duke.internal.Parser;
import duke.internal.Storage;
import duke.task.Deadline;
import duke.task.Task;
import duke.task.TaskList;

/**
 * A command to add a deadline task to the task list.
 * Usage: deadline [1] /by [2]
 * [1]: description
 * [2]: deadline (date)
 */
public class DeadlineCommand extends Command {
    private final String description;
    private final String deadline;

    /**
     * Returns a new DeadlineCommand object.
     *
     * @param description the description of the deadline task
     * @param deadline    the deadline of the task
     * @throws DukeException if the description contains a pipe character `|`
     */
    public DeadlineCommand(String description, String deadline) {
        if (description.contains("|")) {
            throw new DukeException("Description cannot contain the character '|'.");
        }
        this.description = description;
        this.deadline = deadline;
    }

    @Override
    public boolean isTerminal() {
        return false;
    }

    @Override
    public void execute(TaskList tasks, Storage storage, MessageBuilder messageBuilder,
                        Parser parser) throws IOException {
        Task task = new Deadline(description, deadline);
        tasks.addTask(task);
        messageBuilder.addLine("I've added your deadline.")
                .addLine(task.toString())
                .addTaskListSize(tasks);
        storage.saveTasks(tasks);
    }
}
