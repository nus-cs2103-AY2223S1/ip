import java.time.LocalDate;

public class EventCommand extends Command {
    private final Event event;

    public EventCommand(String description, LocalDate time) {
        super();
        this.event = new Event(description, time);
    }

    @Override
    public void execCommand(TaskList list, Ui ui, Storage storage) {
        list.addTask(this.event);
        ui.showAdd(this.event, list.getSize());
        storage.saveList(list.save());
    }
}
