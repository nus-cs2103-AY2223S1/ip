package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

public class UnmarkCommand extends Command {
    int index;

    public UnmarkCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList taskList, Storage storage, Ui ui) throws DukeException {

        taskList.markNotDone(index);
        ui.showUnmark(taskList.getTask(index));
        storage.writeFile(taskList.getTaskList());
    }
}

