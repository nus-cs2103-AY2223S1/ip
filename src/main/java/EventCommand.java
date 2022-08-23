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
        storage.writeFile(t.tasksToString());
        ui.printAddTask(e, t.getSize());
    }
}
