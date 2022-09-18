package duke.command;

import duke.common.Messages;
import duke.storage.Storage;
import duke.util.TaskList;

/**
 * Represents a command to clear the window; subclass of Command.
 * @author Huang Yuchen
 * @author hyuchen@u.nus.edu
 */
public class ClearCommand extends Command {
    /**
     * @param storage  the storage object
     * @param tasklist the tasklist object
     * @return         the bot reply
     */
    @Override
    public String execute(Storage storage, TaskList tasklist) {
        return Messages.CLEAR_COMMAND;
    }
}
