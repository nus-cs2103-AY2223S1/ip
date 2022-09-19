package kohaku.commands;
import java.time.LocalDateTime;
import kohaku.datastruct.TaskList;
import kohaku.datastruct.Pair;
import kohaku.task.Deadlines;
import kohaku.daveexceptions.DaveException;
import kohaku.parser.Parser;

public class AddDeadlineCommand extends Command {

    private TaskList tasks;
    private Deadlines task;

    /**
     * Initialises an add deadline command with a provided tasklist and
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
     * Initialises an add deadline command with a provided tasklist and
     * a String representation of the task to be added.
     *
     * @param tasks Tasklist for the task to added into
     * @param input String representation of the Task to be added
     * @throws DaveException Exception is thrown if addition operation fails
     */
    public AddDeadlineCommand(TaskList tasks, String input) throws DaveException {
        Pair<String, LocalDateTime> taskDetails = Parser.parseTask(input);
        this.task = new Deadlines(taskDetails.getHead(), taskDetails.getTail());
        this.tasks = tasks;
    }

    /**
     * Executes the command.
     * @return The string representation of the result of the command.
     */
    @Override
    public String execute() {
        return this.tasks.add(task);
    }
}
