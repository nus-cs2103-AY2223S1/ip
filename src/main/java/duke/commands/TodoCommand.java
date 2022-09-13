package duke.commands;

import duke.gui.Ui;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.task.Todo;

/**
 * Represents a todo command.
 */
public class TodoCommand extends Command {
    public static final String COMMAND_WORD = "todo";
    public static final String SHORTER_COMMAND_WORD = "t";

    private Todo todoTask;

    /**
     * Creates a new instance of todo command and todo task.
     *
     * @param description The description of the todo task.
     */
    public TodoCommand(String description) {
        this.todoTask = new Todo(description);
    }

    /**
     * Adds the todo task to the task list and save it to the local file.
     * @param taskList The list of tasks in Duke.
     * @param ui The TextUi class used to print message in Duke.
     * @param storage The storage used to save the tasks in the local file.
     * @return The success message after adding a todo task.
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        taskList.addTask(this.todoTask);
        storage.appendTaskToFile(this.todoTask);
        return ui.getAddTaskCommandMessage(this.todoTask, taskList);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
