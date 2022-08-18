package Commands;

import Duke.Storage;
import Duke.TaskList;
import Duke.Ui;
import Tasks.Event;

import java.time.LocalDate;

public class EventCommand extends Command {
    private String desc;
    private LocalDate date;

    public EventCommand(String desc, LocalDate date) {
        super();
        this.desc = desc;
        this.date = date;
    }

    @Override
    public void execute(TaskList t, Ui ui, Storage storage) {
        Event e = new Event(this.desc, this.date);
        t.addTask(e);
        storage.save(t.stringfy());
        ui.printAddTask(e, t.getSize());
    }

}
