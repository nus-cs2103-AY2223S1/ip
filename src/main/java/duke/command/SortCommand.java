package duke.command;

import duke.task.TaskList;

/**
 * This class encapsulates a sort command from the user.
 */
public class SortCommand extends Command {
    public static final String COMMAND_WORD = "sort";

    private TaskList taskList;

    /**
     * Creates a SortCommand with the given TaskList.
     *
     * @param taskList The TaskList.
     */
    public SortCommand(TaskList taskList) {
        this.taskList = taskList;
    }

    /**
     * Returns a String containing the sorted TaskList.
     *
     * @return A String containing the sorted TaskList.
     */
    @Override
    public String execute() {
        this.taskList.sort();
        return "Here are the tasks in your list, sorted:\n"
                + this.taskList;
    }
}
