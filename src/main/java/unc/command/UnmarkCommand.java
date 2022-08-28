package unc.command;

import unc.Storage;
import unc.TaskList;
import unc.Ui;

public class UnmarkCommand extends Command {
    private final int index;

    public UnmarkCommand(int num) {
        this.index = num - 1;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        taskList.markAsNotDone(index);
        ui.unmark(taskList, index);
        storage.save(taskList);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
