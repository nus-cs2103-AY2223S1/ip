import java.time.LocalDate;
import java.time.LocalTime;

public class DeadlineCommand extends Command {

    public static final String COMMAND_WORD = "deadline";

    private String description;
    private LocalDate localDate;
    private LocalTime localTime;

    public DeadlineCommand(String description, LocalDate localDate, LocalTime localTime) {
        this.description = description;
        this.localDate = localDate;
        this.localTime = localTime;
    }

    @Override
    public void execute(TaskList task, Ui ui, Storage storage) {
        Deadline currDeadline = new Deadline(this.description, this.localDate, this.localTime);
        task.addTask(currDeadline);
        ui.displayAddTask(currDeadline);
        ui.displayNumOfTasks(task.getTaskSize());
        ui.displaySeparator();
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
