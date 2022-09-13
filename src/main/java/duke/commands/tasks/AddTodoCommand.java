package duke.commands.tasks;

import duke.commands.CommandResult;
import duke.domain.Todo;

/**
 * AddTodoCommand Class
 */
public class AddTodoCommand extends BaseTaskCommand {
    public static final String COMMAND_WORD = "todo";
    private final Todo todo;
    private String successMessage = "This task has been successfully added!\n";

    /**
     * AddTodoCommand constructor method
     *
     * @param todo
     */
    public AddTodoCommand(Todo todo) {
        this.todo = todo;
    }

    @Override
    public CommandResult execute() {
        this.taskList.addTask(todo);
        successMessage = String.format("%s%s%s", successMessage, "\n", todo);
        return new CommandResult(successMessage);
    }

}
