package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.Event;
import duke.task.TaskList;
import duke.ui.Message;
import duke.ui.Ui;

import java.time.LocalDateTime;

public class AddEventCommand extends Command {
    private String description;
    private LocalDateTime atDate;

    public AddEventCommand(String description, LocalDateTime atDate) {
        this.description = description;
        this.atDate = atDate;
    }

    @Override
    public String execute(TaskList tasks, Storage storage) throws DukeException {
        Event t = new Event(description, atDate);
        tasks.add(t);
        storage.save(tasks);
        return Ui.getTaskStatusString(Message.ADDED, t);
    }
}