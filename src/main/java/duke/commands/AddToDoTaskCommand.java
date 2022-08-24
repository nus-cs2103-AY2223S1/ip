package duke.commands;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import duke.exceptions.DukeException;
import duke.managers.TaskManager;
import duke.managers.UiManager;
import duke.models.task.ToDo;

/**
 * TODO: Add JavaDocs
 */
public class AddToDoTaskCommand extends AddTaskCommand implements Command {
    public static final String COMMAND_WORD = "todo";

    private static final String ERROR_INVALID_TODO_TASK = "Use the 'todo' command together with a task "
            + "description!\nFor example: 'todo borrow book'";

    // Matches a non-empty description, for example: <description>
    private static final Pattern MATCH_TODO_TASK = Pattern.compile("(.+)");

    private final String arguments;

    /**
     * TODO: Add JavaDocs
     */
    public AddToDoTaskCommand(String arguments) {
        this.arguments = arguments;
    }

    @Override
    public void execute(TaskManager taskManager, UiManager uiManager) throws DukeException {
        Matcher matcher = AddToDoTaskCommand.MATCH_TODO_TASK.matcher(this.arguments);
        if (!matcher.matches()) {
            throw new DukeException(AddToDoTaskCommand.ERROR_INVALID_TODO_TASK);
        }

        uiManager.print(this.addTask(taskManager, () -> new ToDo(this.arguments.strip())));
    }
}
