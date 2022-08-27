import java.time.LocalDate;

public class AddCommand extends Command {

    protected TaskType type;
    protected String description;
    protected LocalDate date;

    public AddCommand(TaskType type, String description) {
        isExit = false;
        this.type = type;
        this.description = description;
    }

    public AddCommand(TaskType type, String description, LocalDate date) {
        isExit = false;
        this.type = type;
        this.description = description;
        this.date = date;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws FredException {
        switch (type) {
        case TODO:
            tasks.add(new ToDo(description));
            break;
        case EVENT:
            tasks.add(new Event(description, date));
            break;
        case DEADLINE:
            tasks.add(new Deadline(description, date));
            break;
        }

        ui.showMessage("Got it. I've added this task ");
        ui.showMessage(tasks.getTask(tasks.size()).toString());
        ui.showMessage("Now you have " + tasks.size() + " tasks in your list.");
    }
}
