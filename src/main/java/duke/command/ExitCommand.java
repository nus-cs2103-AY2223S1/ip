package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * A command class that encapsulates the action of exiting Duke.
 */
public class ExitCommand extends Command {

    @Override
    public void execute(TaskList tasks, Storage storage, Ui ui) throws DukeException {
        ui.showBye();
    }

    @Override
    public boolean bye() {
        return true;
    }
}
