package seedu.duke.command;

import seedu.duke.DukeException;
import seedu.duke.operations.Storage;
import seedu.duke.operations.TaskList;
import seedu.duke.operations.Ui;

public class InvalidCommand extends Command {
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        throw new DukeException(ui.getInvalidInputMessage());
    }
}
