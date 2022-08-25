import java.time.LocalDateTime;

public class AddDeadlineCommand extends Command {
    private String description;
    private String by;
    private LocalDateTime byDate;

    AddDeadlineCommand(String description, String by) {
        this.description = description;
        this.by = by;
    }

    AddDeadlineCommand(String description, LocalDateTime byDate) {
        this.description = description;
        this.byDate = byDate;
    }

    @Override
    void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        Deadline deadline;
        if (byDate == null) {
            deadline = new Deadline(description, by);
        } else {
            deadline = new Deadline(description, byDate);
        }
        tasks.add(deadline);
        ui.printMessage("\tGot it. I've added this task:\n\t" +
                deadline.toString() +
                "\n\tNow you have " + tasks.size() + " tasks in the list.");
        storage.save(tasks.getAllTasks());
    }
}
