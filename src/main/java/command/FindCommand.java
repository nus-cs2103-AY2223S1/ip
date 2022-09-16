package command;

import mykoba.Storage;
import mykoba.TaskList;
import mykoba.Ui;

/**
 * This class encapsulates a command asking Koba to find tasks with a given keyword.
 */
public class FindCommand extends Command {
    private String keyword;

    /**
     * Constructs a FindCommand, given a keyword.
     *
     * @param keyword The String representing the matching keyword.
     */
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    /**
     * Finds matching tasks, given a keyword and returns them as a String.
     *
     * @param tasklist The TaskList instance of the task manager.
     * @param ui       The Ui instance of the task manager.
     * @param storage  The Storage instance of the task manager.
     * @return a String listing all the tasks containing keyword.
     */
    @Override
    public String execute(TaskList tasklist, Ui ui, Storage storage) {
        TaskList matchingTasks = tasklist.findTask(keyword);
        return ui.findCommandtoString(matchingTasks);
    }
}
