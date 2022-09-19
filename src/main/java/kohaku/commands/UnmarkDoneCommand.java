package kohaku.commands;
import kohaku.datastruct.TaskList;
import kohaku.task.Task;
import kohaku.daveexceptions.DaveException;

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
                throw new DaveException("Oh no!!! Please tell me which task to unmark!");
            }
            int index = Integer.parseInt(input);
            this.task = tasks.get(index - 1);
        } catch (IndexOutOfBoundsException | NumberFormatException e) {
            throw new DaveException(String.format("Oh no!!! Please give me a valid task to unmark between 1 and %d!", tasks.size()));
        }
    }

    /**
     * Executes the command.
     * @return The string representation of the result of the command.
     */
    @Override
    public String execute() {
        return this.task.unmarkDone();
    }

}
