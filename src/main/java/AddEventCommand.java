import java.time.LocalDate;

public class AddEventCommand extends Command {
    protected LocalDate date;
    protected String taskName;

    public AddEventCommand( String taskName, LocalDate date) {
        this.taskName = taskName;
        this.date = date;
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) {
        Event event = new Event(this.taskName, this.date);
        tasks.add(event);
        ui.showcase("Nephew so busy, got new event:", event.toString());
        ui.show("Nephew now have " + Integer.toString(tasks.getLength()) + " tasks in the list.");
    }
}
