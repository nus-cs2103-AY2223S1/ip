import java.time.LocalDateTime;

public class EventCommand extends Command {

    String name;
    LocalDateTime time;

    public EventCommand(String name, LocalDateTime time) {
        this.name = name;
        this.time = time;
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.add(tasks.createTask('E', name, time));
        ui.printAddTask(name, tasks.size());
    }

}
