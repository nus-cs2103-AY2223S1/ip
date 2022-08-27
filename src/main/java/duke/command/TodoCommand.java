package duke.command;

import duke.Storage;
import duke.Ui;
import duke.exception.DukeException;
import duke.task.Task;
import duke.task.TaskList;
import duke.task.Todo;

/**
 * Represents the command that is executed when the user inputs todo.
 *
 * @author njxue
 * @version v0.1
 */
public class TodoCommand extends Command {
    /** Description of the todo task. */
    private String description;

    /**
     * Creates a TodoCommand.
     *
     * @param description Description of the todo task.
     */
    public TodoCommand(String description) {
        this.description = description;
    }

    /**
     * Returns the format of the todo command.
     *
     * @return The format of the todo command.
     */
    public static String getFormat() {
        return "todo <String>";
    }

    /**
     * Executes the todo command. Creates and adds a new todo task.
     *
     * @param tasks TaskList to add the newly created Todo into.
     * @param ui Ui object which interacts with the user.
     * @param storage Storage object which loads and saves tasks.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        Task todo = new Todo(description);
        tasks.add(todo);
        ui.showAddTask(todo, tasks);
        storage.save(tasks);
    }

    /**
     * Returns false, because todo is not an application terminating command.
     *
     * @return False.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
