package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.util.TaskList;

/**
 * Represents a general command to be run in the parser; superclass of all other commands.
 * @author Huang Yuchen
 * @author hyuchen@u.nus.edu
 */
public abstract class Command {
    /**
     * Executes the command.
     * This is the main way for outputting bot replies.
     *
     * @param storage        the storage object
     * @param tasklist       the tasklist object
     * @return               the bot reply
     * @throws DukeException if the user input is unrecognised
     */
    public abstract String execute(Storage storage, TaskList tasklist) throws DukeException;
}
