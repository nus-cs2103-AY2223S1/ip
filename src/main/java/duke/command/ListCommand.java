package duke.command;

import duke.task.TaskList;

/**
 * This class encapsulates a list command from the user.
 */
public class ListCommand extends Command {
    // Solution below adapted from https://github.com/teikjun/duke
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
     * Returns a String containing the TaskList.
     *
     * @return A String containing the TaskList.
     */
    @Override
    public String execute() {
        return "Here are the tasks in your list:\n"
                + this.taskList;
    }
}
