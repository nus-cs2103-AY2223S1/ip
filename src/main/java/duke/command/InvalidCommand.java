package duke.command;

import duke.DukeException;
import duke.operations.Storage;
import duke.operations.TaskList;
import duke.operations.Ui;

public class InvalidCommand extends Command {
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        throw new DukeException(ui.getInvalidInputMessage());
    }
}
