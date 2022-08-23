package Command;

import Duke.Storage;
import Duke.TaskList;
import Duke.Ui;
import Tasks.Deadline;

import java.time.LocalDate;

public class DeadlineCommand extends Command {
    private String desc;
    private LocalDate date;

    public DeadlineCommand(String desc, LocalDate date) {
        super();
        this.desc = desc;
        this.date = date;
    }

    @Override
    public void execute(TaskList t, Ui ui, Storage storage) {
        Deadline d = new Deadline(this.desc, this.date);
        t.addTask(d);
        storage.writeFile(t.tasksToString());
        ui.printAddTask(d, t.getSize());
    }
}
