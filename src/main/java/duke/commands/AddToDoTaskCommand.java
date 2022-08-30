package duke.commands;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import duke.exceptions.DukeException;
import duke.managers.TaskManager;
import duke.models.task.ToDo;

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

    private static final String ERROR_INVALID_TODO_TASK = "Use the 'todo' command together with a task "
            + "description!\nFor example: 'todo borrow book'";

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
    public String execute(TaskManager taskManager) throws DukeException {
        Matcher matcher = AddToDoTaskCommand.MATCH_TODO_TASK.matcher(this.arguments);
        if (!matcher.matches()) {
            throw new DukeException(AddToDoTaskCommand.ERROR_INVALID_TODO_TASK);
        }

        return this.addTask(taskManager, () -> new ToDo(this.arguments.strip()));
    }
}
