package duke.command;

import duke.Storage;
import duke.Task;
import duke.TaskList;
import duke.Ui;
import duke.exceptions.DukeException;

public class DeleteCommand extends Command {

    private int deleteTask;

    public DeleteCommand(int deleteTask) {
        this.deleteTask = deleteTask;
    }

    @Override
    public void execute(Ui ui, TaskList taskList, Storage storage) throws DukeException {
        Task success = taskList.deleteTask(this.deleteTask);
        storage.save(taskList);
        ui.showDeleteSuccess(success, taskList.numOfTask());
    }
}
