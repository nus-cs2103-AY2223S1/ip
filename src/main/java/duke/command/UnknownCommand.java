package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * UnknownCommand represents the commands that the user inputs that are not recognised by duke.
 */
public class UnknownCommand extends Command {

    /**
     * Returns an exception when the command line input does not match the required input.
     *
     * @param tasks List of tasks.
     * @param ui User interface for duke.
     * @param storage Storage information for tasks.
     * @return String output to be displayed by duke.
     * @throws DukeException
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        throw new DukeException("Input not recognised! Please try again! ");
    }
}
