package betago.commands;

import betago.Storage;
import betago.TaskList;

/**
 * ListCommand class that shows the current tasks in the list.
 */
public class ListCommand implements Command {
    /**
     * Returns a list of the current tasks in the tasklist.
     *
     * @param tasks Tasklist that stores the various tasks.
     * @param storage Storage to load and save files.
     * @param str Input from the user.
     * @return Output to the user after executing the command.
     */
    @Override
    public String execute(TaskList tasks, Storage storage, String str) {
        return tasks.listItems();
    }
}
