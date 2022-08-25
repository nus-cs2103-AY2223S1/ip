package duke.command;

import duke.model.TaskList;
import duke.storage.Storage;
import duke.ui.Ui;

public class MarkCommand extends Command {

    private int taskNumber;

    public MarkCommand(int taskNumber) {
        this.taskNumber = taskNumber;
    }

    @Override
    public void execute(TaskList taskList, Storage storage, Ui ui) {
        taskList.mark(this.taskNumber);
        storage.save(taskList);
        ui.mark(taskList.getTask(this.taskNumber));
    }
}
