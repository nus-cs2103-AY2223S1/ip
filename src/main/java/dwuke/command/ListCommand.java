package dwuke.command;

import dwuke.task.TaskList;

/**
 * This class encapsulates a list command from the user.
 */
public class ListCommand extends Command {
    public static final String COMMAND_WORD = "list";
    private TaskList taskList;

    /**
     * Creates a ListCommand with the given TaskList.
     *
     * @param taskList The TaskList.
     */
    public ListCommand(TaskList taskList) {
        this.taskList = taskList;
    }

    /**
     * Returns a String containing the given TaskList.
     *
     * @return A String containing the TaskList.
     */
    @Override
    public String execute() {
        return "hewe awe da tasks in youw wist:\n" + this.taskList;
    }
}
