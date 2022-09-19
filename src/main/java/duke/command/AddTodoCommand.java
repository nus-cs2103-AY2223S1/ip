package duke.command;

import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.task.Todo;

/**
 * Command to add an Todo to the list of the tasks.
 */
public class AddTodoCommand extends Command {
    public static final String COMMAND_WORD = "todo";

    private final String desc;

    /**
     * Creates a Command to add a Todo.
     *
     * @param desc Description of the Todo task.
     */
    public AddTodoCommand(String desc) {
        this.desc = desc;
    }

    /**
     * Adds a Todo task to the list of tasks.
     *
     * @param tasks List of tasks.
     * @param storage Storage for the list of tasks.
     * @return Result of the execution.
     */
    @Override
    public String execute(TaskList tasks, Storage storage) {
        Task newTask = new Todo(desc);
        tasks.addTask(newTask);
        storage.write(tasks);
        return "Got it. I've added this task:\n\t" + newTask + "\nNow you have " + tasks.size() + " tasks in the list.";
    }
}
