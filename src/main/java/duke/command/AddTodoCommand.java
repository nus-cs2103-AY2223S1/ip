package duke.command;

import java.util.Arrays;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

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
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.addTodo(taskDescription);
        ui.printTaskAddedMessage(tasks.getLatestTask(), tasks.getTotalTasks());
    }
}
