package duke.command;

import java.time.LocalDateTime;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.Event;
import duke.task.TaskList;
import duke.ui.Message;
import duke.ui.Ui;

/**
 * Represents an executable command to add a new Event object
 */
public class AddEventCommand extends Command {
    private String description;
    private LocalDateTime atDate;

    public AddEventCommand(String description, LocalDateTime atDate) {
        assert description != null : "Description cannot be null";
        assert atDate != null : "Date cannot be null";
        this.description = description;
        this.atDate = atDate;
    }

    /**
     * Returns a task added status string after adding task to list.
     *
     * @param tasks TaskList object to add the task to.
     * @param storage Storage object to manage save file.
     * @return Task added status string.
     */
    @Override
    public String execute(TaskList tasks, Storage storage) throws DukeException {
        Event t = new Event(description, atDate);
        tasks.add(t);
        storage.save(tasks);
        return Ui.getTaskStatusString(Message.ADDED, t);
    }
}
