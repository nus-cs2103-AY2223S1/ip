package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

public class ListCommand extends Command {
    @Override
    public void execute(Ui ui, Storage storage, TaskList taskList) throws DukeException {
        ui.displayList(taskList.getTaskList());
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
