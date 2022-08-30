package Commands;
import DataStruct.TaskList;
import Task.Task;
import DaveExceptions.DaveException;

public class UnmarkDoneCommand extends Command {

    private final Task task;

    /**
     * Initialises an unmark done command with a task to be unmarked.
     *
     * @param task the Task to be unmarked
     */
    public UnmarkDoneCommand(Task task) {
        this.task = task;
    }

    /**
     * Initialises a unmark done command with a provided tasklist and
     * index of task to be unmarked.
     *
     * @param tasks Tasklist containing the task to be unmarked
     * @param input Index of the Task to be unmarked in String format
     */
    public UnmarkDoneCommand(TaskList tasks, String input) throws DaveException {
        try {
            if (input.equals("")) {
                throw new DaveException("( ; ω ; ) Oh nyo!!! Please tell me which task to unmark!");
            }
            int index = Integer.parseInt(input);
            this.task = tasks.get(index - 1);
        } catch (IndexOutOfBoundsException | NumberFormatException e) {
            throw new DaveException(String.format("( ; ω ; ) Oh nyo!!! Please give me a valid task to unmark between 1 and %d!", tasks.size()));
        }
    }

    @Override
    public String execute() {
        return this.task.unmarkdone();
    }

}
