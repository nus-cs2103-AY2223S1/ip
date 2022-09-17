package duke.commands;

import duke.task.Todo;

/**
 * Creates and stores a todo.
 */
public class CreateTodoCommand extends CreateTaskCommand {
    /**
     * Constructor for CreateTodoCommand.
     * @param todo the todo to be stored
     */
    public CreateTodoCommand(Todo todo) {
        super(todo);
    }
}
