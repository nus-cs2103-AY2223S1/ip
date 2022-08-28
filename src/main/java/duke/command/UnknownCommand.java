package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

public class UnknownCommand extends Command {

    /**
     * Returns an exception when the command line input does not match the required input.
     *
     * @param tasks List of tasks.
     * @param ui User interface for duke.
     * @param storage Storage information for tasks.
     * @throws DukeException
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        throw new DukeException("Input not recognised! Please try again! ");
    }
}
