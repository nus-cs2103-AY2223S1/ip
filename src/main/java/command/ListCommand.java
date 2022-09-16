package command;

import mykoba.Storage;
import mykoba.TaskList;
import mykoba.Ui;

/**
 * This class encapsulates a command asking Koba to list the tasks stored.
 */
public class ListCommand extends Command {

    /**
     * Returns all the Tasks currently in the TaskList.
     *
     * @param tasklist The TaskList instance for the task manager.
     * @param ui       The Ui instance for the task manager.
     * @param storage  The Storage instance for the task manager.
     * @return a String listing all the current tasks in table format.
     */
    @Override
    public String execute(TaskList tasklist, Ui ui, Storage storage) {
        return ui.listtoString(tasklist);
    }
}
