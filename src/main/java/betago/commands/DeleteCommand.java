package betago.commands;

import betago.DukeException;
import betago.Storage;
import betago.TaskList;

/**
 * DeleteCommand class that deletes the specific task in the tasklist.
 */
public class DeleteCommand implements Command {

    /**
     * Deletes a task in the tasklist.
     *
     * @param tasks Tasklist that stores the various tasks.
     * @param storage Storage to load and save files.
     * @param str Input from the user.
     * @return Output to the user after executing the command.
     */
    @Override
    public String execute(TaskList tasks, Storage storage, String str) {
        try {
            String output = tasks.deleteItems(str);
            storage.saveItems();
            return output;
        } catch (DukeException e) {
            return e.getMessage();
        }
    }
}
