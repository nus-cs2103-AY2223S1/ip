package Commands;
import java.time.LocalDateTime;
import DataStruct.TaskList;
import DataStruct.Pair;
import Task.Events;
import DaveExceptions.DaveException;
import Parser.Parser;

public class AddEventCommand implements Command {

    private TaskList tasks;
    private Events task;

    /**
     * Initialises a add Event command with a provided tasklist and
     * task to be added.
     *
     * @param tasks Tasklist for the task to added into
     * @param task the Task to be added
     */
    public AddEventCommand(TaskList tasks, Events task) {
        this.tasks = tasks;
        this.task = task;
    }

    /**
     * Initialises a add Event command with a provided tasklist and
     * a String representation of the task to be added.
     *
     * @param tasks Tasklist for the task to added into
     * @param input String representation of the Task to be added
     * @throws DaveException Exception is thrown if addition operation fails
     */
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
