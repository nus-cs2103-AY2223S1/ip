package duke;

import java.time.LocalDate;

public class DeadlineCommand extends Command {
    private String description;
    private LocalDate by;

    public DeadlineCommand(String description, LocalDate by) {
        super();
        this.description = description;
        this.by = by;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        Task deadline = new Deadline(this.description, this.by);
        tasks.addTask(deadline);
        storage.save(tasks);
        return ui.showAddTask(deadline, tasks.getSize());
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
