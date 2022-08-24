import java.time.LocalDateTime;

public class AddDeadlineCommand extends Command {
    private String description;
    private LocalDateTime by;

    public AddDeadlineCommand(String description, LocalDateTime by) {
        this.description = description;
        this.by = by;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        Task task = new Deadline(description, by);
        tasks.add(task);
        ui.addSuccess(task,tasks);
        storage.save(tasks);
    }
}
