package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.Deadline;
import duke.task.TaskList;
import duke.ui.Message;
import duke.ui.Ui;

import java.time.LocalDateTime;

public class AddDeadlineCommand extends Command {
    private String description;
    private LocalDateTime byDate;

    public AddDeadlineCommand(String description, LocalDateTime byDate) {
        this.description = description;
        this.byDate = byDate;
    }

    @Override
    public String execute(TaskList tasks, Storage storage) throws DukeException {
        Deadline t = new Deadline(description, byDate);
        tasks.add(t);
        storage.save(tasks);
        return Ui.getTaskStatusString(Message.ADDED, t);
    }
}