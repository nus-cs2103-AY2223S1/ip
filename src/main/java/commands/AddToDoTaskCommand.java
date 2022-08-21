package commands;

import exceptions.DukeException;
import managers.TaskManager;
import models.task.ToDo;
import managers.UiManager;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AddToDoTaskCommand extends AddTaskCommand implements Command {
    public static final String COMMAND_WORD = "todo";
    private static final String INVALID_TODO_TASK_ERROR = "Use the 'todo' command together with a task " +
            "description!\nFor example: 'todo borrow book'";

    // Matches a non-empty description, for example: <description>
    private static final Pattern MATCH_TODO_TASK = Pattern.compile("(.+)");

    private final String arguments;

    public AddToDoTaskCommand(String arguments) {
        this.arguments = arguments;
    }

    @Override
    public void execute(TaskManager taskManager, UiManager uiManager) throws DukeException {
        Matcher matcher = AddToDoTaskCommand.MATCH_TODO_TASK.matcher(this.arguments);
        if (!matcher.matches()) {
            throw new DukeException(AddToDoTaskCommand.INVALID_TODO_TASK_ERROR);
        }

        uiManager.print(this.addTask(taskManager, () -> new ToDo(this.arguments.strip())));
    }
}
