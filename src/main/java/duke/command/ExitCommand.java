package duke.command;

import duke.common.DukeException;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

public class ExitCommand extends Command {
    public ExitCommand() {}

    @Override
    public void execute(Ui ui, Storage storage, TaskList taskList) throws DukeException {
        ui.showOutput("Bye. Hope to see you again soon!\n");
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
