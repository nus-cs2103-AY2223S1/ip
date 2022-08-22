package commands;

import data.exception.DukeException;
import storage.Storage;
import data.TaskList;
import ui.Ui;
import tasks.Task;

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
