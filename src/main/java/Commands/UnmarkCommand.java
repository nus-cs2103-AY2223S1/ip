package Commands;

import Duke.Storage;
import Duke.TaskList;
import Duke.Ui;
import Tasks.Task;

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
    public void execute(Ui ui, Storage storage, TaskList taskList) {
        Task taskToUnmark = taskList.getTask(taskNumber - 1);
        taskToUnmark.markAsNotDone();
        storage.save(taskList);
        ui.printUnmarkTask(taskToUnmark);
    }
}
