package duke.command;

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
    public String execute(TaskList taskList) {
        Todo todo = new Todo(description);
        return taskList.add(todo);
    }
}
