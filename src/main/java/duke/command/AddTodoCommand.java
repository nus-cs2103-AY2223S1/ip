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

    public AddTodoCommand(String desc) {
        this.desc = desc;
    }

    @Override
    public String execute(TaskList tasks, Storage storage) {
        Task newTask = new Todo(this.desc);
        tasks.addTask(newTask);
        storage.write(tasks);
        return "Got it. I've added this task:\n\t" + newTask + "\nNow you have " + tasks.size() + " tasks in the list.";
    }
}
