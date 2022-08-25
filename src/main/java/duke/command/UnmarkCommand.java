package duke.command;

import duke.model.TaskList;
import duke.storage.Storage;
import duke.ui.Ui;

public class UnmarkCommand extends Command {

    private int taskNumber;

    public UnmarkCommand(int taskNumber) {
        this.taskNumber = taskNumber;
    }

    @Override
    public void execute(TaskList taskList, Storage storage, Ui ui) {
        taskList.unmark(this.taskNumber);
        storage.save(taskList);
        ui.unmark(taskList.getTask(this.taskNumber));
    }
}
