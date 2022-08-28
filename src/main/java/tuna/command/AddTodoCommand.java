package tuna.command;

import java.util.Arrays;

import tuna.Storage;
import tuna.TaskList;
import tuna.Ui;

/**
 * Represents an add Todo Task command. An AddTodoCommand object contains the task description.
 */
public class AddTodoCommand extends Command {

    /** Description of the task */
    private String taskDescription;

    /**
     * Creates a AddTodoCommand object.
     *
     * @param inputs String array containing the user inputs.
     */
    public AddTodoCommand(String[] inputs) {
        super(CommandType.TODO);
        taskDescription = String.join(" ", Arrays.copyOfRange(inputs, 1, inputs.length));
    }

    /**
     * Executes the add todo command, adding the Todo Task to the tasks list.
     *
     * @param tasks TaskList object.
     * @param ui Ui object.
     * @param storage Storage object.
     */
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.addTodo(taskDescription);
        return ui.taskAddedMessage(tasks.getLatestTask(), tasks.getTotalTasks());
    }
}
