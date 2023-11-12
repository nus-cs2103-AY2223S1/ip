package duke;

import java.time.LocalDate;

public class DeadlineCommand extends Command {
    private String description;
    private LocalDate by;

    /**
     * Constructor of deadline command
     *
     * @param description description of the deadline
     * @param by date of the task
     */

    public DeadlineCommand(String description, LocalDate by) {
        super();
        this.description = description;
        this.by = by;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        Task deadline = new Deadline(this.description, this.by);
        String addTaskMessage = ui.showAddTask(deadline, tasks.getSize());
        if (tasks.checkDuplicates(deadline)) {
            addTaskMessage += "\n" + ui.showDuplicateMessage();
        }
        tasks.addTask(deadline);
        storage.save(tasks);
        return addTaskMessage;
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
