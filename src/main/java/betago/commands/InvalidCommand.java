package betago.commands;

import betago.Storage;
import betago.TaskList;

/**
 * InvalidCommand class that returns an error statement to the user.
 */
public class InvalidCommand implements Command {
    private String message;

    /**
     * Constructor for InvalidCommand class.
     *
     * @param message Output message to the user.
     */
    public InvalidCommand(String message) {
        this.message = message;
    }

    /**
     * Returns an error statement to the user.
     *
     * @param tasks Tasklist that stores the various tasks.
     * @param storage Storage to load and save files.
     * @param str Input from the user.
     * @return Error statement to the user.
     */
    @Override
    public String execute(TaskList tasks, Storage storage, String str) {
        return this.message;
    }
}
