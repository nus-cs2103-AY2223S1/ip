package duke.command;

import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.ui.Ui;

public class DeleteCommand extends Command {

    private int integer;

    public DeleteCommand(int integer) {
        this.integer = integer;
    }

    @Override
    public boolean isExit() {
        return false;
    }
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        ui.showTaskDeleted(taskList, integer - 1);
        taskList.remove(integer);
        storage.saveFile(taskList);

    }
}
