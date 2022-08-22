package commands;

import data.exception.DukeException;
import storage.Storage;
import data.TaskList;
import ui.Ui;
import tasks.Task;

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
    public void execute(Ui ui, Storage storage, TaskList taskList) throws DukeException {
        Task taskToMark = taskList.getTask(taskNumber - 1);
        taskToMark.markAsDone();
        storage.save(taskList);
        ui.printMarkTask(taskToMark);
    }
}
