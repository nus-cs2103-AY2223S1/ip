package duke.command;

import duke.Ui;
import duke.task.TaskList;
import duke.task.Todo;

public class TodoCommand implements Command {
    public static final String COMMAND_WORD = "todo";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Add a todo task. Parameters: DESCRIPTION. Example: "
            + COMMAND_WORD + " borrow book";

    private String description;

    public TodoCommand(String description) {
        this.description = description;
    }

    @Override
    public void execute(TaskList taskList, Ui ui) {
        Todo todo = new Todo(description);
        taskList.add(todo);
        ui.printMessage(ui.wrapMessage("Got it. I've added this task:", todo.toString(), taskList));
    }
}
