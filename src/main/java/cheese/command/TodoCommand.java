package cheese.command;

import cheese.data.TaskList;
import cheese.exception.CheeseException;
import cheese.storage.Storage;
import cheese.task.Task;
import cheese.task.Todo;
import cheese.ui.Response;

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
     * @throws CheeseException If there is an error in saving.
     */
    @Override
    public String execute(TaskList taskList, Storage storage) throws CheeseException {
        Task addedTask = taskList.add(new Todo(description));
        storage.save(taskList);
        return Response.getAddTaskMessage(addedTask, taskList.getSize());
    }
}
