package roofus.command;

import roofus.RoofusException;
import roofus.Storage;
import roofus.TaskList;
import roofus.Ui;

public class FindCommand extends Command {
    private String key;
    public FindCommand(String key) {
        this.key = key;
    }
    
    @Override
    public void execute(TaskList taskList,
                        Storage storage, Ui ui) {
        ui.filterList(taskList, key);
    }
    
    @Override
    public boolean isRunning() {
        return true;
    }
}
