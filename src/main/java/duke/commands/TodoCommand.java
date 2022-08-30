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
     * Adds a Todo task to the list of tasks.
     * @param taskList List of tasks.
     * @param ui Shows the Task added and the total number of tasks on the list.
     * @param storage Saves the modified list of tasks.
     * @return The message indicating that the Todo task has been added and the number of tasks on the list.
     * @throws DukeException If there is an error saving the modified list of tasks.
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        Todo todo = new Todo(description);
        taskList.addTask(todo);
        storage.save(taskList);
        return ui.showTaskAdded(todo) + ui.showNumberOfTasks(taskList.numTasks());
    }
}
