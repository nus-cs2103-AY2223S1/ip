package Commands;

import Duke.DukeException;
import Duke.Storage;
import Duke.TaskList;
import Duke.Ui;
import Tasks.Task;

public class DeleteCommand extends Command {

    private int taskNumber;

    public DeleteCommand(int taskNumber) {
        this.taskNumber = taskNumber;
    }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public void execute(Ui ui, Storage storage, TaskList taskList) throws DukeException {
        Task deletedTask = taskList.deleteTask(taskNumber);
        storage.save(taskList);
        ui.printDeleteTask(deletedTask, taskList.getSize());
    }
}
