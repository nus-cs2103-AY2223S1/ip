package duke.command;

import java.time.LocalDateTime;

import duke.storage.Storage;
import duke.task.Deadline;
import duke.task.TaskList;
import duke.ui.Message;
import duke.ui.Ui;

/**
 * Represents an executable command to add a new Deadline object.
 */
public class AddDeadlineCommand extends Command {
    private String description;
    private LocalDateTime byDate;

    public AddDeadlineCommand(String description, LocalDateTime byDate) {
        assert description != null: "Description cannot be null";
        assert byDate != null: "Date cannot be null";
        this.description = description;
        this.byDate = byDate;
    }

    /**
     * Returns a task added status string after adding task to list.
     *
     * @param tasks TaskList object to add the task to.
     * @param storage Storage object to manage save file.
     * @return Task added status string.
     */
    @Override
    public String execute(TaskList tasks, Storage storage) {
        Deadline t = new Deadline(description, byDate);
        tasks.add(t);
        storage.save(tasks);
        return Ui.getTaskStatusString(Message.ADDED, t);
    }
}
