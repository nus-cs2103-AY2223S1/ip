package duke.command;

import duke.exception.DukeException;
import duke.main.Storage;
import duke.main.TaskList;
import duke.main.Ui;
import duke.task.Deadline;

import java.time.LocalDateTime;

public class AddDeadlineCommand extends Command {
    private String description;
    private String by;
    private LocalDateTime byDate;

    public AddDeadlineCommand(String description, String by) {
        this.description = description;
        this.by = by;
    }

    public AddDeadlineCommand(String description, LocalDateTime byDate) {
        this.description = description;
        this.byDate = byDate;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        Deadline deadline;
        if (byDate == null) {
            deadline = new Deadline(description, by);
        } else {
            deadline = new Deadline(description, byDate);
        }

        tasks.add(deadline);
        ui.printMessage("\tGot it. I've added this task:\n\t" +
                deadline.toString() +
                "\n\tNow you have " + tasks.size() + " tasks in the list.");
        storage.save(tasks.getAllTasks());
    }
}
