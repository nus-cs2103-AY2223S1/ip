package dwuke.command;

import dwuke.task.Todo;
import dwuke.task.TaskList;

/**
 * This class encapsulates a to-do command from the user.
 */
public class TodoCommand extends AddCommand {
    public static final String COMMAND_WORD = "todo";

    /**
     * Creates a TodoCommand with the given TaskList and To-do.
     *
     * @param taskList The TaskList.
     * @param todo The To-do.
     */
    public TodoCommand(TaskList taskList, Todo todo) {
        super(taskList, todo);
    }
}
