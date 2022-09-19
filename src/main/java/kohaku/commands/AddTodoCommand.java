package kohaku.commands;
import kohaku.datastruct.TaskList;
import kohaku.task.ToDo;
import kohaku.daveexceptions.DaveException;

public class AddTodoCommand extends Command {

    private TaskList tasks;
    private ToDo task;

    /**
     * Initialises an add ToDo command with a provided tasklist and
     * task to be added.
     *
     * @param tasks Tasklist for the task to added into
     * @param task the Task to be added
     */
    public AddTodoCommand(TaskList tasks, ToDo task) {
        this.tasks = tasks;
        this.task = task;
    }

    /**
     * Initialises an add ToDo command with a provided tasklist and
     * a String representation of the task to be added.
     *
     * @param tasks Tasklist for the task to added into
     * @param input String representation of the Task to be added
     * @throws DaveException Exception is thrown if addition operation fails
     */
    public AddTodoCommand(TaskList tasks, String input) throws DaveException {
        if (input.equals("")) {
            throw new DaveException("( ; ω ; ) Oh nyo!!! The description of a todo cannot be empty!");
        }
        this.task = new ToDo(input);
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

