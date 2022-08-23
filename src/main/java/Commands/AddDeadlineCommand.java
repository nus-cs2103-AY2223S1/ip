package Commands;
import java.time.LocalDateTime;
import DataStruct.TaskList;
import DataStruct.Pair;
import Task.Deadlines;
import DaveExceptions.DaveException;
import Parser.Parser;

public class AddDeadlineCommand implements Command {

    private TaskList tasks;
    private Deadlines task;

    public AddDeadlineCommand(TaskList tasks, Deadlines task) {
        this.tasks = tasks;
        this.task = task;
    }

    public AddDeadlineCommand(TaskList tasks, String input) throws DaveException {
        Pair<String, LocalDateTime> taskDetails = Parser.parseDeadline(input);
        this.task = new Deadlines(taskDetails.getHead(), taskDetails.getTail());
        this.tasks = tasks;
    }

    @Override
    public String execute() {
        return this.tasks.add(task);
    }
}
