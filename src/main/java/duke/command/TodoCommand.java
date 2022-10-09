package duke.command;

import duke.task.TaskList;
import duke.task.Todo;

/**
 * This class encapsulates a to-do command from the user.
 */
public class TodoCommand extends AddCommand {
    // Solution below adapted from https://github.com/teikjun/duke
    public static final String COMMAND_WORD = "todo";

    /**
     * Creates a TodoCommand with the given TaskList and To-do.
     *
     * @param taskList The TaskList.
     * @param todo     The To-do.
     */
    public TodoCommand(TaskList taskList, Todo todo) {
        super(taskList, todo);
    }
}
