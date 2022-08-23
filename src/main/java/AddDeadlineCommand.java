import java.time.LocalDate;

public class AddDeadlineCommand extends Command {
    protected LocalDate date;
    protected String taskName;

    public AddDeadlineCommand(String taskName, LocalDate date) {
        this.taskName = taskName;
        this.date = date;
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) {
        Deadline deadline = new Deadline(this.taskName, this.date);
        tasks.add(deadline);
        ui.showcase("Nephew got new deadline, hurry up before I beat you:", deadline.toString());
        ui.show("Nephew now have " + Integer.toString(tasks.getLength()) + " tasks in the list.");
    }
}
