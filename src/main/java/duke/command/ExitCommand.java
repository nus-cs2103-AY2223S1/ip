package duke.command;

import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.ui.Ui;

public class ExitCommand implements Command {
    @Override
    public void execute(TaskList itemList, Ui ui, Storage storage) {
        itemList.save(storage);
        ui.showOutro();
        System.exit(0);
    }
}
