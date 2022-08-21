package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

import java.time.LocalDate;

public class DeadlineCommand extends Command{
    private String description;
    private LocalDate date;

    public DeadlineCommand(String description, LocalDate date) {
        super(false);
        this.description = description;
        this.date = date;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.addDeadline(description, date);
        int size = tasks.getSize();
        ui.showAdd(tasks.getTask(size - 1), size);
        storage.save(tasks.saveToStorage());
    }
}
