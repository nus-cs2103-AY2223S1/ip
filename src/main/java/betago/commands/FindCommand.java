package betago.commands;

import betago.DukeException;
import betago.Storage;
import betago.TaskList;

/**
 * FindCommand class that searches for the tasks that contains the specific keyword in their description.
 */
public class FindCommand implements Command {

    /**
     * Finds tasks that contain the specific keyword in their description.
     *
     * @param tasks Tasklist that stores the various tasks.
     * @param storage Storage to load and save files.
     * @param str Input from the user.
     * @return Output to the user after executing the command.
     */
    @Override
    public String execute(TaskList tasks, Storage storage, String str) {
        try {
            String output = tasks.findTasks(str);
            return output;
        } catch (DukeException e) {
            return e.getMessage();
        }
    }
}
