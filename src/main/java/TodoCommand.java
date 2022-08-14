/**
 * This class encapsulates a to-do command from the user.
 */
public class TodoCommand extends AddCommand {
    public static final String COMMAND_WORD = "todo";

    TodoCommand(TaskList taskList, Todo todo) {
        super(taskList, todo);
    }
}
