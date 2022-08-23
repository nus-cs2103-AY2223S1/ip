package roofus.command;

import roofus.RoofusException;
import roofus.Storage;
import roofus.TaskList;
import roofus.Ui;

public class UnmarkCommand extends Command {
    private int index;

    public UnmarkCommand(int index) {
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
        taskList.unMark(index);
        ui.unmark(taskList.getTask(index - 1));
    }

    @Override
    public boolean isRunning() {
        return true;
    }
}
