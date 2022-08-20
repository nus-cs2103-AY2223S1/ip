import java.time.LocalDateTime;

public class EventCommand extends Command {
    private final String description;
    private final LocalDateTime atDateTime;

    public EventCommand(String description, LocalDateTime atDateTime) {
        this.description = description;
        this.atDateTime = atDateTime;
    }

    public static String getFormat() {
        return "event <String> /at <String>";
    }
    
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        Task event = new Event(this.description, this.atDateTime);
        tasks.add(event);
        ui.showAddTask(event, tasks);
        storage.save(tasks);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
