package betago.commands;

import betago.DukeException;
import betago.Storage;
import betago.TaskList;

/**
 * LoadCommand class that loads data from specific file.
 */
public class LoadCommand implements Command {
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
        try {
            return storage.loadNewFile(str);
        } catch (DukeException e) {
            return e.getMessage();
        }
    }
}
