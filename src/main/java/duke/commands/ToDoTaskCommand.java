package duke.commands;

import duke.exception.DukeException;
import duke.main.Storage;
import duke.tasks.TaskList;
import duke.tasks.ToDoTask;

/**
 * ToDoCommand that creates a new TodoTask and prints message to user
 *
 * @author Sharmaine Teo Hai Zhen
 */
public class ToDoTaskCommand extends TaskCommand {

    /**
     * Constructor for TodoCommand
     *
     * @param description Description of TodoTask to be created
     * @throws DukeException in case of missing task description
     */
    public ToDoTaskCommand(String description) throws DukeException {
        super(description);
        assert description.split(" ")[0].equals("todo") : "Keyword should be todo for ToDoTaskCommand";
    }

    /**
     * Creates new TodoTask and prints message to user
     *
     * @return @inheritDoc
     */
    @Override
    public String execute(TaskList tasks, Storage storage) throws DukeException {
        ToDoTask task = new ToDoTask(this.description);
        tasks.add(task);
        storage.save(tasks);
        return super.getMessage(tasks, task);
    }
}
