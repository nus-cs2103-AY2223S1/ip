package duke.command;

import duke.logic.TaskList;

/**
 * FindCommand is a command for Duke to find events using a keyword.
 *
 * @author totsukatomofumi
 */
public class FindCommand extends Command {
    /** Task list the command has to search from. */
    private TaskList taskList;

    /** Specified keyword for searching events. */
    private String keyword;

    /**
     * Constructor for the command.
     *
     * @param taskList the task list the command will search from.
     * @param keyword the keyword to search with.
     */
    public FindCommand(TaskList taskList, String keyword) {
        this.taskList = taskList;
//        assert keyword.length() > 0;
        this.keyword = keyword;
    }

    /**
     * Lists matching tasks with descriptions containing the specified keyword.
     */
    @Override
    public String get() {
        return "Here are the matching tasks in your list:\n"
                + this.taskList.search(this.keyword).toString();
    }
}
