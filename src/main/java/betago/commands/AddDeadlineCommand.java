package betago.commands;

import betago.DukeException;
import betago.Storage;
import betago.TaskList;

/**
 * AddDeadlineCommand class that adds a deadline task into the tasklist upon execution.
 */
public class AddDeadlineCommand implements Command {

    /**
     * Adds a deadline task to the tasklist.
     *
     * @param tasks Tasklist that stores the various tasks.
     * @param storage Storage to load and save files.
     * @param str Input from the user.
     * @return Output to the user after executing the command.
     */
    @Override
    public String execute(TaskList tasks, Storage storage, String str) {
        try {
            String output = tasks.addDeadline(str);
            storage.saveItems();
            return output;
        } catch (DukeException e) {
            return e.getMessage();
        }
    }
}
