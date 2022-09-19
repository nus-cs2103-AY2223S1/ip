package kohaku.commands;
import kohaku.datastruct.*;
import kohaku.daveexceptions.DaveException;
import kohaku.task.Task;


public class MarkDoneCommand extends Command{

    private Task task;

    /**
     * Initialises a mark done command with a task to be marked.
     *
     * @param task the Task to be marked
     */
    public MarkDoneCommand(Task task) {
        this.task = task;
    }

    /**
     * Initialises a mark done command with a provided tasklist and
     * index of task to be marked.
     *
     * @param tasks Tasklist containing the task to be marked
     * @param input Index of the Task to be marked in String format
     */
    public MarkDoneCommand(TaskList tasks, String input) throws DaveException {
        try {
            if (input.equals("")) {
                throw new DaveException("Oh no!!! Please tell me which task to mark!");
            }
            int index = Integer.parseInt(input);
            this.task = tasks.get(index - 1);
        } catch (IndexOutOfBoundsException | NumberFormatException e) {
            throw new DaveException(String.format("Oh no!!! Please give me a valid task to mark between 1 and %d!", tasks.size()));
        }
    }

    /**
     * Executes the command.
     * @return The string representation of the result of the command.
     */
    @Override
    public String execute() {
        return this.task.markDone();
    }
}
