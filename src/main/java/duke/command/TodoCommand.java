package duke.command;

import duke.task.Todo;

public class TodoCommand extends TaskCommand {

    public static final String COMMAND_NAME = "todo";

    public TodoCommand(Todo newTodo) {
        super(newTodo);
    }
}
