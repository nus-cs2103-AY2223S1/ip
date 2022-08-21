package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

import java.util.Arrays;

public class AddTodoCommand extends Command {

    private String taskDescription;

    public AddTodoCommand(String[] inputs) {
        super(CommandType.TODO);
        taskDescription = String.join(" ", Arrays.copyOfRange(inputs, 1, inputs.length));
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.addTodo(taskDescription);
        ui.printTaskAddedMessage(tasks.getLatestTask(), tasks.getTotalTasks());
    }
}
