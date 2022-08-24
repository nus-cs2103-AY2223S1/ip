package duke.commands;

import duke.exceptions.DukeException;
import duke.managers.TaskManager;
import duke.managers.UiManager;
import duke.models.task.ToDo;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Encapsulates a command for adding a {@link ToDo} task. The command should be used as follows:
 * <ul>
 *     <li>
 *         {@code todo {taskDescription}}: To add a todo task with the corresponding description.
 *     </li>
 * </ul>
 *
 * @author Emily Ong Hui Qi
 */
public class AddToDoTaskCommand extends AddTaskCommand implements Command {
    public static final String COMMAND_WORD = "todo";
    private static final String INVALID_TODO_TASK_ERROR = "Use the 'todo' command together with a task " +
            "description!\nFor example: 'todo borrow book'";

    /**
     * Matches a non-empty description, for example: {@code {taskDescription}}.
     */
    private static final Pattern MATCH_TODO_TASK = Pattern.compile("(.+)");

    private final String arguments;

    /**
     * Creates a new instance of the Command handler for adding a {@link ToDo} task.
     *
     * @param arguments The arguments following the {@code 'todo'} prefix supplied by the user from keyboard input
     */
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
