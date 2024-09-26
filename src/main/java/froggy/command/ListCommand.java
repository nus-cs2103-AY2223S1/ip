package froggy.command;

import froggy.storage.Storage;
import froggy.task.TaskList;
import froggy.ui.Ui;

/**
 * A class which extends from the Command abstract class.
 * A ListCommand object can be used to list all the items in a TaskList object.
 */
public class ListCommand extends Command {
    /**
     * Creates a ListCommand object.
     */
    public ListCommand() {
        super(false);
    }

    /**
     * Prints out all the items in the list.
     *
     * @param tasks The TaskList object containing all the tasks and CRUD methods to modify the tasks.
     * @param ui The Ui object capable of displaying user interface.
     * @param storage The storage object capable of doing write, load, open functionality.
     * @return the reply from the bot
     */
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        String messageToUser = ui.showList(tasks);
        return messageToUser;
    }
}
