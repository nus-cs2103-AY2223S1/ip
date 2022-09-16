package duke.command;

import java.io.IOException;

import duke.internal.MessageBuilder;
import duke.internal.Parser;
import duke.internal.Storage;
import duke.task.Event;
import duke.task.Task;
import duke.task.TaskList;

/**
 * A command to add an event task to the task list.
 * Usage: event [0] /at [1]
 * [0]: description
 * [1]: date
 */
public class EventCommand extends Command {
    private final String description;
    private final String datetime;

    /**
     * Returns a new EventCommand object.
     *
     * @param description the description of the event task
     * @param datetime    the date and time of the event
     * @throws IllegalArgumentException if the description contains a pipe character `|`
     */
    public EventCommand(String description, String datetime) {
        if (description.contains("|")) {
            throw new IllegalArgumentException("Description cannot contain the character '|'.");
        }
        this.description = description;
        this.datetime = datetime;
    }

    @Override
    public boolean isTerminal() {
        return false;
    }

    @Override
    public void execute(TaskList tasks, Storage storage, MessageBuilder messageBuilder,
                        Parser parser) throws IOException {
        Task task = new Event(description, datetime);
        tasks.addTask(task);
        messageBuilder.addLine("I've added your event.")
                .addLine(task.toString())
                .addTaskListSize(tasks);
        storage.saveTasks(tasks);
    }
}
