package duke.command;

import duke.Ui;
import duke.exception.DukeException;
import duke.processor.TaskList;

/**
 * Class to represent the command 'bye'.
 *
 * @author Melissa Anastasia Harijanto
 */
public class ByeCommand extends Command {
    /**
     * Prints the exit lines when the user types 'bye'.
     *
     * @param tasks The list of tasks that the user has inputted.
     */
    @Override
    public void execute(TaskList tasks) throws DukeException {
        Ui ui = new Ui();
        ui.exit();
    }
}
