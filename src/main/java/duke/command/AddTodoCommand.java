package duke.command;

import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.task.Todo;
import duke.ui.Ui;

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
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        Task newTask = new Todo(this.desc);
        tasks.addTask(newTask);
        ui.showAddTask(newTask, tasks);
        storage.write(tasks);
    }
}
