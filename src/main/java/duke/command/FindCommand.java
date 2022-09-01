package duke.command;

import duke.exception.IllegalKeywordException;
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
     * @throws IllegalKeywordException If no keyword is specified, including just whitespaces.
     */
    public FindCommand(TaskList taskList, String keyword) throws IllegalKeywordException {
        this.taskList = taskList;
        //double check only
        if (keyword.length() > 0) {
            this.keyword = keyword;
        } else {
            throw new IllegalKeywordException("No keyword.");
        }
    }

    /**
     * Lists matching tasks with descriptions containing the specified keyword.
     */
    @Override
    public void run() {
        System.out.println("Here are the matching tasks in your list:");
        System.out.print(this.taskList.search(this.keyword).toString());
    }
}
