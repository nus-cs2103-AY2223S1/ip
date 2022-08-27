import java.time.LocalDate;

public class AddCommand extends Command {
    private TaskType taskType;
    private String description;
    private LocalDate taskDate;

    public AddCommand(TaskType taskType, String description, LocalDate taskDate) {
        this.taskType = taskType;
        this.description = description;
        this.taskDate = taskDate;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        Task task = null;
        switch (taskType) {
            case TODO:
                task = new Todo(description);
                break;
            case DEADLINE:
                task = new Deadline(description, taskDate);
                break;
            case EVENT:
                task = new Event(description, taskDate);
                break;
        }
        tasks.add(task);
        storage.save(tasks);
        ui.printString("added:" + task);
    }
}
