package duke.commands;

import duke.data.TaskList;
import duke.data.exception.DukeException;
import duke.storage.Storage;
import duke.tasks.Todo;
import duke.ui.Ui;

/**
 * Represents the command to add a Todo task to the list of tasks.
 */
public class TodoCommand extends Command {
    public static final String COMMAND_WORD = "todo";
    private String description;

    /**
     * Constructor for a MarkCommand.
     * @param description The description of the task.
     */
    public TodoCommand(String description) {
        super();
        this.description = description;
    }

    /**
     * Checks if the Command is a ByeCommand.
     * @return False.
     */
    @Override
    public boolean isBye() {
        return false;
    }

    /**
     * Adds a Todo task to the list of tasks.
     * @param taskList List of tasks.
     * @param ui Shows the Task added and the total number of tasks on the list.
     * @param storage Saves the modified list of tasks.
     * @throws DukeException If there is an error saving the modified list of tasks.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        Todo todo = new Todo(this.description);
        taskList.addTask(todo);
        storage.save(taskList);
        ui.showTaskAdded(todo);
        ui.showNumberOfTasks(taskList.numTasks());
    }
}
