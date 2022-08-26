package duke.command;

import duke.model.Task;
import duke.model.TaskList;
import duke.storage.Storage;
import duke.ui.Ui;

public class DeleteCommand extends Command {

    private int taskNumber;

    public DeleteCommand(int taskNumber) {
        this.taskNumber = taskNumber;
    }
    @Override
    public void execute(TaskList taskList, Storage storage, Ui ui) {
        ui.delete(taskList.delete(this.taskNumber));
        storage.save(taskList);
    }
}
