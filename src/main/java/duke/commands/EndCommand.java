package duke.commands;

import duke.data.TaskList;
import duke.storage.Storage;
import duke.ui.Ui;


public class EndCommand extends Command{

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        storage.save(taskList);
        return;
    }

    @Override
    public boolean isEnd() {
        return true;
    }
}
