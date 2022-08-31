package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.ui.Ui;
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
     * @param storage the storage object
     * @param tasklist the task list object
     * @param ui the user interface object
     * @throws DukeException if the user input is unrecognised
     */
    public void execute(Storage storage, TaskList tasklist, Ui ui) throws DukeException {
        ui.showGoodbye();
        storage.writeToFile(tasklist);
        System.exit(0);
    }
}
