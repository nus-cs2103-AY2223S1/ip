package duke.commands;

import duke.ui.Ui;
import duke.data.TaskList;
import duke.data.exception.DukeException;
import duke.storage.Storage;
import duke.tasks.Task;

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
