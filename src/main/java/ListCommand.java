/**
 * This class encapsulates a list command from the user.
 */
public class ListCommand extends Command {
    public static final String COMMAND_WORD = "list";

    ListCommand() {
    }

    /**
     * Returns a String containing the given task list.
     *
     * @param taskList The task list to return.
     * @return A String containing the task list.
     */
    @Override
    public String execute(TaskList taskList) {
        return "Choo choo! Here are the tasks in your list:\n" + taskList;
    }
}
