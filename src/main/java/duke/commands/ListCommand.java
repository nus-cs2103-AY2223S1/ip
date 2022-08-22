package duke.commands;

import duke.ui.Ui;
import duke.data.TaskList;
import duke.storage.Storage;

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
