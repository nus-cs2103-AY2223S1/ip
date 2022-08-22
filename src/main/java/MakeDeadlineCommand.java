import java.time.LocalDate;

public class MakeDeadlineCommand extends Command {
    private String description;
    private LocalDate time;

    public MakeDeadlineCommand(String description, LocalDate time) {
        this.description = description;
        this.time = time;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        Deadline newTask = new Deadline(this.description, this.time);
        taskList.addTask(newTask);
        ui.showTaskAdded(newTask, taskList.getTaskListLength());
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
