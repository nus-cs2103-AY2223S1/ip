package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.util.TaskList;

/**
 * Represents a command to exit the program; subclass of Command.
 * @author Huang Yuchen
 * @author hyuchen@u.nus.edu
 */
public class ExitCommand extends Command {
    /**
     * Executes the command for "bye" keyword.
     * This is the main way for outputting bot replies.
     *
     * @param storage        the storage object
     * @param tasklist       the task list object
     * @return               the bot reply
     * @throws DukeException if the user input is unrecognised
     */
    @Override
    public String execute(Storage storage, TaskList tasklist) throws DukeException {
        storage.writeToFile(tasklist);
        System.exit(0);
        return null;
    }
}
