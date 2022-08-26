package duke.command;

import duke.task.Deadline;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

import java.time.LocalDate;

public class DeadlineCommand extends Command {

    private String description;
    private LocalDate date;

    public DeadlineCommand(String description, LocalDate date) {
        this.description = description;
        this.date = date;
    }
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        Deadline deadline = new Deadline(description, date);
        tasks.add(deadline);
        ui.DeadlineTask(tasks, deadline);
        storage.update(tasks.getTasks());
    }
}
