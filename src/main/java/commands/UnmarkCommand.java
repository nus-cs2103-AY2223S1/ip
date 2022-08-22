package commands;

import data.exception.DukeException;
import storage.Storage;
import data.TaskList;
import ui.Ui;
import tasks.Task;

public class UnmarkCommand extends Command {

    private int taskNumber;

    public UnmarkCommand(int taskNumber) {
        this.taskNumber = taskNumber;
    }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public void execute(Ui ui, Storage storage, TaskList taskList) throws DukeException {
        Task taskToUnmark = taskList.getTask(taskNumber - 1);
        taskToUnmark.markAsNotDone();
        storage.save(taskList);
        ui.printUnmarkTask(taskToUnmark);
    }
}
