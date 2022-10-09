package duke.command;

import duke.task.TaskList;

/**
 * This class encapsulates a find command from the user.
 */
public class FindCommand extends Command {
    public static final String COMMAND_WORD = "find";

    private TaskList taskList;
    private String keyword;

    /**
     * Creates a FindCommand with the given TaskList and keyword.
     *
     * @param taskList The TaskList.
     * @param keyword  The keyword.
     */
    public FindCommand(TaskList taskList, String keyword) {
        this.taskList = taskList;
        this.keyword = keyword;
    }

    /**
     * Finds all Tasks in the TaskList containing the keyword.
     *
     * @return A String containing the list of Tasks that match the keyword.
     */
    @Override
    public String execute() {
        TaskList matchingTasks = this.taskList.findTasks(this.keyword);
        return "Here are the matching tasks in your list:\n"
                + matchingTasks;
    }
}
