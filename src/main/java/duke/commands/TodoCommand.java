package duke.commands;

import duke.exceptions.DukeException;
import duke.storage.Storage;
import duke.tasks.TaskList;
import duke.tasks.Todo;
import duke.ui.Ui;

/**
 * Command to add a todo task to the list of tasks
 */
public class TodoCommand extends Command {

    public static final String COMMAND_WORD = "todo";
    private static final String MESSAGE_SUCCESS = "Yo, I managed to add this task: \n";

    private Todo todo;

    /**
     * Constructs the TodoCommand instance
     *
     * @param description the description of the task
     */
    public TodoCommand(String description) {
        super();
        todo = new Todo(description);
    }

    /**
     * Returns a boolean value true if the command is a bye command,
     * false otherwise.
     *
     * @return a boolean value on whether the command is a bye command
     */
    @Override
    public boolean isByeCommand() {
        return false;
    }

    /**
     * Executes the command to add the specified todo task to the
     * list of tasks
     *
     * @param tasks The current list of tasks
     * @param ui The Ui instance to return the result to the user
     * @param storage The Storage instance to store the result to local storage
     * @return the string representation of the execution result
     * @throws DukeException if errors are encountered during execution
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        tasks.add(todo);
        String result = MESSAGE_SUCCESS + todo + " " + tasks.showNumberOfTasks();
        ui.showMessage(result);
        return result;
    }
}