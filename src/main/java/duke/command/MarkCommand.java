package duke.command;

import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.ui.Ui;

public class MarkCommand extends Command {

    private int integer;

    public MarkCommand(int integer) {
        this.integer = integer;
    }
    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        taskList.markAsDone(integer);
        ui.showMarkAsDone(taskList, integer);
        storage.saveFile(taskList);
    }
}
