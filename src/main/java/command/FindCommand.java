package command;

import myduke.Storage;
import myduke.TaskList;
import myduke.Ui;

public class FindCommand extends Command {
    private String keyword;

    /**
     * Constructor for a FindCommand, given a keyword.
     *
     * @param keyword The String representing the matching keyword.
     */
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    /**
     * Method that finds matching tasks, given a keyword.
     *
     * @param tasklist The TaskList instance of the task manager.
     * @param ui       The Ui instance of the task manager.
     * @param storage  The Storage instance of the task manager.
     */
    @Override
    public String execute(TaskList tasklist, Ui ui, Storage storage) {
        TaskList matchingTasks = tasklist.findTask(keyword);
        return ui.findCommandtoString(matchingTasks);
    }
}
