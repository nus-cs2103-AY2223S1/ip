package duke.commands;

import duke.task.Todo;

public class CreateTodoCommand extends CreateTaskCommand {
    public CreateTodoCommand(Todo todo) {
        super(todo);
    }
}