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

    /**
     * Initialises a add deadline command with a provided tasklist and
     * task to be added.
     *
     * @param tasks Tasklist for the task to added into
     * @param task the Task to be added
     */
    public AddDeadlineCommand(TaskList tasks, Deadlines task) {
        this.tasks = tasks;
        this.task = task;
    }

    /**
     * Initialises a add deadline command with a provided tasklist and
     * a String representation of the task to be added.
     *
     * @param tasks Tasklist for the task to added into
     * @param input String representation of the Task to be added
     * @throws DaveException Exception is thrown if addition operation fails
     */
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
