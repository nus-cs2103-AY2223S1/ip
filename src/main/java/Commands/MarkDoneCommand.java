package Commands;
import DataStruct.*;
import DaveExceptions.DaveException;
import DaveExceptions.DaveNoTasksException;
import Task.Task;


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
                throw new DaveException("( ; ω ; ) Oh nyo!!! Please tell me which task to mark!");
            }
            int index = Integer.parseInt(input);
            this.task = tasks.get(index - 1);
        } catch (IndexOutOfBoundsException | NumberFormatException e) {
            throw new DaveException(String.format("( ; ω ; ) Oh nyo!!! Please give me a valid task to mark between 1 and %d!", tasks.size()));
        }
    }

    @Override
    public String execute() {
        return this.task.markdone();
    }
}
