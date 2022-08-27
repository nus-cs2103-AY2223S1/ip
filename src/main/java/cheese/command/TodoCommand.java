package cheese.command;

import cheese.data.TaskList;
import cheese.storage.Storage;
import cheese.task.Task;
import cheese.task.Todo;
import cheese.ui.Ui;

/**
 * Represents a user command to create a new todo.
 */
public class TodoCommand extends Command {
    /** Description of new todo. */
    private String description;

    /**
     * Constructs an instance of <code>TodoCommand</code>
     * 
     * @param description Description of new todo.
     */
    public TodoCommand(String description) {
        this.description = description;
    }

    /**
     * Executes operations to create a new todo, add todo to list, and save list.
     * 
     * @param {@inheritDoc}
     */
    @Override
    public void execute(TaskList taskList, Storage storage, Ui ui) {
        Task addedTask = taskList.add(new Todo(description));
        ui.showAddTask(addedTask, taskList.getSize());
        storage.save(taskList);
    }
}
