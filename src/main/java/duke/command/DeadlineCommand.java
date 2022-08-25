package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

import java.time.LocalDate;

public class DeadlineCommand extends Command {
    String description;
    LocalDate doBy;

    public DeadlineCommand(String description, LocalDate doBy) {
        super();
        this.description = description;
        this.doBy =  doBy;
    }

    @Override
    public void run(TaskList tasks, Ui ui, Storage storage) {
        String task = tasks.addDeadline(this.description, this.doBy);
        ui.printAdd(task, tasks.getSize());
        storage.save(tasks.getTaskList());
    }
}
