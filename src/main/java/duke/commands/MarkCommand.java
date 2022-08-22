package duke.commands;

import duke.ui.Ui;
import duke.data.TaskList;
import duke.data.exception.DukeException;
import duke.storage.Storage;
import duke.tasks.Task;

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
