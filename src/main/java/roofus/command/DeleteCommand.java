package roofus.command;

import roofus.RoofusException;
import roofus.Storage;
import roofus.TaskList;
import roofus.Ui;

public class DeleteCommand extends Command {
    private int index;
    
    public DeleteCommand(int index) {
        this.index = index;
    }
    
    @Override
    public void execute(
            TaskList taskList, Storage storage, Ui ui) {
        try {
            if (index > taskList.length() || index < 1) {
                throw new RoofusException("Hey! It's not even in this list!");
            }
        } catch (RoofusException err){
            ui.printErrMessage(err.getMessage());
        }
        taskList.delete(index);
        ui.delete(taskList.getTask(index - 1).toString(), 
                taskList.length());
    }
    
    @Override
    public boolean isRunning() {
        return true;
    }
}
