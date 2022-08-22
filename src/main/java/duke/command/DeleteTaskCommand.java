package duke.command;

import duke.command.Command;
import duke.main.Storage;
import duke.main.TaskList;
import duke.main.Ui;

public class DeleteTaskCommand extends Command {
    private int index;

    public DeleteTaskCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        if (!taskList.isValidIndex(this.index)) {
            ui.showInvalidIndex();
            return;
        }
        ui.showTaskDeleted(taskList.deleteTask(index), taskList.getTaskListLength());
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
