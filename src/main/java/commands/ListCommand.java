package commands;

import storage.Storage;
import data.TaskList;
import ui.Ui;

public class ListCommand extends Command {

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public void execute(Ui ui, Storage storage, TaskList taskList) {
        String list = taskList.list();
        ui.printList(list);
    }
}
