package iana.command;

import iana.main.Storage;
import iana.main.Ui;
import iana.tasks.TaskList;

/**
 * Command to tasks with given keyword.
 */
public class FindCommand extends Command {
    private String keyword;
    
    /**
     * Constructor for FindCommand class.
     * @param keyword the specified word used to search for tasks.
     */
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    /**
     * Gets task list containing tasks containing specified keyword.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        TaskList list = tasks.findKeyword(this.keyword);
        ui.list(list);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
