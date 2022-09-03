import java.time.LocalDateTime;

public class EventCommand extends Command {
    public static final String COMMAND_WORD = "event";

    private Event eventTask;

    public EventCommand(String description, LocalDateTime deadlineTiming) {
        this.eventTask = new Event(description, deadlineTiming);
    }

    @Override
    public void execute(TaskList tasks, TextUi ui, Storage storage) {
        tasks.addTask(this.eventTask);
        storage.appendTaskToFile(this.eventTask);
        ui.showAddTaskMessage(this.eventTask, tasks);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
