package duke.command;

import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.ui.Ui;

public class UnmarkCommand extends Command {

    private int integer;

    public UnmarkCommand(int integer) {
        this.integer = integer;
    }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        taskList.markAsNotDone(integer);
        ui.showMarkAsNotDone(taskList, integer);
        storage.saveFile(taskList);
    }
}
