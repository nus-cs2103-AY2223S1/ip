package duke.command;

import duke.Storage;
import duke.Ui;
import duke.exception.DukeException;
import duke.task.Task;
import duke.task.TaskList;
import duke.task.Todo;

/**
 * Represents the command that is executed when the user inputs <code>todo</code>.
 *
 * @author njxue
 * @version v0.1
 */
public class TodoCommand extends Command {
    /** Description of the todo task. */
    private String description;
    
    /**
     * Returns the format of the <code>todo</code> command.
     *
     * @return The format of the <code>todo</code> command.
     */
    public static String getFormat() {
        return "todo <String>";
    }

    /**
     * Creates a <code>TodoCommand</code>.
     *
     * @param description Description of the todo task.
     */
    public TodoCommand(String description) {
        this.description = description;
    }

    /**
     * Executes the <code>todo</code> command. 
     * Creates and adds a new todo task.
     * 
     * @param tasks <code>TaskList</code> to add the newly created <code>Todo</code> into.
     * @param ui <code>Ui</code> object which interacts with the user.
     * @param storage <code>Storage</code> object which loads and saves tasks.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        Task todo = new Todo(description);
        tasks.add(todo);
        ui.showAddTask(todo, tasks);
        storage.save(tasks);
    }

    /**
     * Returns false, because <code>todo</code> is not an application terminating command.
     *
     * @return False.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
