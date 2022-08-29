package seedu.duke.command;

import seedu.duke.DukeException;
import seedu.duke.operations.Storage;
import seedu.duke.operations.TaskList;
import seedu.duke.operations.Ui;

/**
 * Command handles the case where the user input is invalid.
 */
public class InvalidCommand extends Command {

    /**
     * Throws DukeException as this Command is only created when an
     * invalid input is given.
     *
     * @param tasks             TaskList of Duke
     * @param ui                Ui of Duke
     * @param storage           Storage of Duke
     * @throws DukeException    Always
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        throw new DukeException(ui.getInvalidInputMessage());
    }
}
