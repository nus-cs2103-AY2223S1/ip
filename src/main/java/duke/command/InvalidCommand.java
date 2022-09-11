package duke.command;

import duke.common.Messages;
import duke.storage.Storage;
import duke.util.TaskList;

/**
 * Represents a command to output unrecognised user inputs; subclass of Command.
 * @author Huang Yuchen
 * @author hyuchen@u.nus.edu
 */
public class InvalidCommand extends Command {
    /**
     * Executes the command for unrecognised user inputs.
     * This is the main way for outputting bot replies.
     *
     * @param storage  the storage object
     * @param tasklist the task list object
     * @return         the bot reply
     */
    @Override
    public String execute(Storage storage, TaskList tasklist) {
        return Messages.UNKNOWN_COMMAND;
    }
}
