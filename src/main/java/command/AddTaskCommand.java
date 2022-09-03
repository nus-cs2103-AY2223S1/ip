package command;

import task.Task;
import task.TaskList;
import ui.Ui;

/**
 * <h1>AddTaskCommand class</h1>
 * Class that adds the generated Task to the TaskList
 */
public abstract class AddTaskCommand extends Command {
    protected static final String EMPTY_TASK_NAME_ERROR_MESSAGE = "Eh you never added your task name";
    protected static final String EMPTY_STRING = "";

    /**
     * Creates the AddTaskCommand
     *
     * @param tasks the list of Tasks.
     * @param input the input String from the user
     * @param ui the Ui object that handles the User Interface.
     */
    public AddTaskCommand(TaskList tasks, String input, Ui ui) {
        super(tasks, input, ui);
    }

    protected abstract boolean isEmptyDescription(String[] splitTask);

    protected void addTask(Task input) {
        tasks.add(input);
    }
}
