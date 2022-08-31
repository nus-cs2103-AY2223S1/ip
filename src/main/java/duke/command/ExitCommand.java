package duke.command;

import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.ui.Ui;

/**
 * Encapsulates saving and quitting Apollo.
 *
 * @author Kartikeya
 */
public class ExitCommand implements Command {
    /**
     * {@inheritDoc}
     * Saves itemList to storage, shows outro to the user and exits from Apollo.
     */
    @Override
    public String execute(TaskList itemList, Ui ui, Storage storage) {
        itemList.save(storage);
        return "Goodbye, see you soon!";
    }
}
