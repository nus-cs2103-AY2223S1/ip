package unc.command;

import unc.Storage;
import unc.TaskList;
import unc.Ui;

public class ListCommand extends Command {
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        ui.displayList(taskList);
        storage.save(taskList);
    }
    @Override
    public boolean isExit() {
        return false;
    }
}
