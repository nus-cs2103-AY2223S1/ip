package duke.command;

import duke.DukeException;
import duke.TaskList;
import duke.task.Task;
import duke.task.ToDoTask;

/**
 * Represents a command to add a new todo item to the list.
 */
public class AddToDoCommand extends Command {
    private final String description;

    public AddToDoCommand(String description) {
        assert description.length() > 0 : "AddToDoCommand description should not be empty";
        this.description = description;
    }

    /**
     * {@inheritDoc}
     */
    public String execute(TaskList tasks) throws DukeException {
        Task tTask = new ToDoTask(description);
        return tasks.addTask(tTask);
    }
}
