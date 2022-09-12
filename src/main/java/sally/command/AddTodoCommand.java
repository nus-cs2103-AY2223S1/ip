package sally.command;

import sally.exception.SallyException;
import sally.task.TaskList;
import sally.storage.Storage;
import sally.ui.Ui;
import sally.task.ToDo;

/**
 * AddTodoCommand class to represent command to add a new todo in the task list.
 *
 * @author liviamil
 */
public class AddTodoCommand extends Command {
    private final String description;

    /**
     * AddTodoComamnd constructor to add todo tasks
     *
     * @param description todo task description
     */
    public AddTodoCommand(String description) {
        this.description = description;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws SallyException {
        ToDo todo = new ToDo(description);
        tasks.addTask(todo);
        ui.showAddedTask(tasks);
        storage.savesFile(tasks);
    }

    @Override
    public boolean isBye() {
        return false;
    }
}
