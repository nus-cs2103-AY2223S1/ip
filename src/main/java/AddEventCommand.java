import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class AddEventCommand implements Command {

    private TaskList tasks;
    private Events task;

    public AddEventCommand(TaskList tasks, Events task) {
        this.tasks = tasks;
        this.task = task;
    }

    public AddEventCommand(TaskList tasks, String input) throws DaveException {
        Pair<String, LocalDateTime> taskDetails = Parser.parseEvent(input);
        this.task = new Events(taskDetails.getHead(), taskDetails.getTail());
        this.tasks = tasks;
    }

    @Override
    public String execute() {
        return this.tasks.add(task);
    }
}
