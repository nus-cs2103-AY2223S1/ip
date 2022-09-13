package unc.command;

import unc.Storage;
import unc.TaskList;
import unc.Ui;
import unc.UncException;

public class SortCommand extends Command{

    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        taskList.sort();
        storage.save(taskList);
        return ui.displayList(taskList);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
