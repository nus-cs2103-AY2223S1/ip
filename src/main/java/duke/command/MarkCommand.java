package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

public class MarkCommand extends Command{
    int index;

    public MarkCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList taskList, Storage storage, Ui ui) throws DukeException {

        taskList.markDone(index);
        ui.showMark(taskList.getTask(index));
        storage.writeFile(taskList.getTaskList());
    }
}
