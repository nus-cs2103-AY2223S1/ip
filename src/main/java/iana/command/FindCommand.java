package iana.command;

import iana.tasks.TaskList;
import iana.ui.Ui;

/**
 * Command to tasks with given keyword.
 */
public class FindCommand extends Command {
    private String keyword;
    
    /**
     * Constructor for FindCommand class.
     * 
     * @param keyword the specified word used to search for tasks.
     */
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    /**
     * Gets task list containing tasks containing specified keyword.
     */
    @Override
    public String execute(TaskList tasks, Ui ui) {
        TaskList list = tasks.findKeyword(this.keyword);
        return ui.list(list);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
