package Commands;

import Duke.Storage;
import Duke.TaskList;
import Duke.Ui;
import Tasks.Task;

public class MarkCommand extends Command {

    private int taskNumber;

    public MarkCommand(int taskNumber) {
        super();
        this.taskNumber = taskNumber;
    }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public void execute(Ui ui, Storage storage, TaskList taskList) {
        Task taskToMark = taskList.getTask(taskNumber - 1);
        taskToMark.markAsDone();
        storage.save(taskList);
        ui.printMarkTask(taskToMark);
    }
}
