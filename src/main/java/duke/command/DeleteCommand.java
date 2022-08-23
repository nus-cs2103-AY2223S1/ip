package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

public class DeleteCommand extends Command {
    int index;

    public DeleteCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList taskList, Storage storage, Ui ui) throws DukeException {
        ui.showDelete(taskList.getTask(index), taskList.size()-1);
        taskList.delete(this.index);
        storage.writeFile(taskList.getTaskList());
    }
}
