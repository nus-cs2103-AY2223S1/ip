package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

import java.time.LocalDate;

public class DeadlineCommand extends Command {
    String description;
    LocalDate by;

    public DeadlineCommand(String description, LocalDate by) {
        super();
        this.description = description;
        this.by = by;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        String task = tasks.addDeadline(this.description, this.by);
        ui.showAdd(task, tasks.getSize());
        storage.save(tasks.saveTasks());
    }
}