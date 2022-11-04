package duke.command;

import duke.task.Todo;

/**
 * This command encapsulates a Todo object and inserts it into
 * the task list when executed.
 */
public class TodoCommand extends TaskCommand {

    /** The keyword to run TodoCommand. */
    public static final String COMMAND_NAME = "todo";

    /**
     * Constructs a TodoCommand object encapsulating the specified parameter.
     *
     * @param newTodo the specified Todo parameter.
     */
    public TodoCommand(Todo newTodo) {
        super(newTodo);
    }
}
