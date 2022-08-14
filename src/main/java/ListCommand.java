/**
 * This class encapsulates a list command from the user.
 */
public class ListCommand extends Command {
    public static final String COMMAND_WORD = "list";
    private TaskList taskList;

    ListCommand(TaskList taskList) {
        this.taskList = taskList;
    }

    /**
     * Returns a String containing the given task list.
     *
     * @return A String containing the task list.
     */
    @Override
    public String execute() {
        return "hewe awe da tasks in youw wist:\n" + this.taskList;
    }
}
