package duke.command;

import java.time.LocalDate;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

public class EventCommand extends Command{
    private String description;
    private LocalDate date;

    public EventCommand(String description, LocalDate date) {
        super(false);
        this.description = description;
        this.date = date;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.addEvent(description, date);
        int size = tasks.getSize();
        ui.showAdd(tasks.getTask(size - 1), size);
        storage.save(tasks.saveToStorage());
    }
}
