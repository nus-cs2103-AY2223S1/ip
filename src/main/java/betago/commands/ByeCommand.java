package betago.commands;

import betago.Storage;
import betago.TaskList;

/**
 * ByeCommand class that returns the farewell statement.
 */
public class ByeCommand implements Command {

    /**
     * Returns the farewell statement.
     *
     * @param tasks Tasklist that stores the various tasks.
     * @param storage Storage to load and save files.
     * @param str Input from the user.
     * @return Farewell statement to the user.
     */
    @Override
    public String execute(TaskList tasks, Storage storage, String str) {
        return "Goodbye Human. Till next time.\n";
    }
}
