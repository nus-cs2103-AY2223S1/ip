package roofus.command;

import roofus.Storage;
import roofus.TaskList;
import roofus.Ui;

public class ClearCommand extends Command {

    @Override
    public void execute(TaskList taskList,
                        Storage storage, Ui ui) {
        taskList.clearStorage();
        ui.clearStorage();
    }
    
    @Override
    public boolean isRunning() {
        return true;
    }
}
