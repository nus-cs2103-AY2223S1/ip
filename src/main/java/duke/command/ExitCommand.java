package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

public class ExitCommand extends Command{
    @Override
    public void execute(Ui ui, Storage storage, TaskList tasklist) throws DukeException {
        ui.formatMessage("Bye. Hope to see you again soon!");
    }

    @Override
    public boolean isRunning() {
        return false;
    }
}
