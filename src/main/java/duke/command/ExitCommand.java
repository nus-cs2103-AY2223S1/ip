package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

public class ExitCommand extends Command {

    public ExitCommand() {}

    @Override
    public void execute(Ui ui, Storage storage, TaskList taskList) throws DukeException {

    }

    @Override
    public boolean isExit() {
        return true;
    }
}
