package duke.command;

import java.time.LocalDate;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

public class DeadlineCommand extends Command {
    private String description;
    private LocalDate by;

    public DeadlineCommand(String description, LocalDate by) {
        super();
        this.description = description;
        this.by = by;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        String task = tasks.addDeadline(description, by);
        ui.showAdd(task, tasks.getSize());
        storage.save(tasks.saveTasks());
    }
}
