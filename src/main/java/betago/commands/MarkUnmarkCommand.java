package betago.commands;

import betago.DukeException;
import betago.Storage;
import betago.TaskList;

/**
 * MarkUnmarkCommand class that marks the specific task in the tasklist.
 */
public class MarkUnmarkCommand implements Command {
    /**
     * Marks the specific task in the tasklist.
     *
     * @param tasks Tasklist that stores the various tasks.
     * @param storage Storage to load and save files.
     * @param str Input from the user.
     * @return Output to the user after executing the command.
     */
    @Override
    public String execute(TaskList tasks, Storage storage, String str) {
        try {
            String output = tasks.markUnmarkItems(str);
            storage.saveItems();
            return output;
        } catch (DukeException e) {
            return e.getMessage();
        }
    }
}
