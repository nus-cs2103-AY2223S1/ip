package handlers;

import exceptions.DukeException;
import models.TaskManager;
import models.ToDo;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AddToDoTaskCommand extends AddTaskCommand implements DukeCommand {
    private static final String INVALID_TODO_TASK_ERROR = "Use the 'todo' command together with a task " +
            "description!\nFor example: 'todo borrow book'";

    // Matches a non-empty description, for example: <description>
    private static final Pattern MATCH_TODO_TASK = Pattern.compile("(.+)");

    @Override
    public String execute(TaskManager taskManager, String arguments) throws DukeException {
        Matcher matcher = AddToDoTaskCommand.MATCH_TODO_TASK.matcher(arguments);
        if (!matcher.find()) {
            throw new DukeException(AddToDoTaskCommand.INVALID_TODO_TASK_ERROR);
        }

        return this.addTask(taskManager, () -> new ToDo(arguments.strip()));
    }
}
