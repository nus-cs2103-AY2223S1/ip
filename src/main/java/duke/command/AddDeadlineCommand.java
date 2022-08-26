package duke.command;

import java.time.LocalDateTime;

import duke.errors.DukeException;
import duke.main.Storage;
import duke.main.Ui;
import duke.task.Deadline;
import duke.task.Task;
import duke.task.TaskList;

public class AddDeadlineCommand extends Command {
    private String description;
    private LocalDateTime by;

    public AddDeadlineCommand(String description, LocalDateTime by) {
        this.description = description;
        this.by = by;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        Task task = new Deadline(description, by);
        tasks.add(task);
        ui.addSuccess(task, tasks);
        storage.save(tasks);
    }
}
