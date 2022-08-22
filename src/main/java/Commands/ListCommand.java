package Commands;

import Duke.Storage;
import Duke.TaskList;
import Duke.Ui;

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
