package roofus.command;

import roofus.RoofusException;
import roofus.Storage;
import roofus.TaskList;
import roofus.Ui;

public class MarkCommand extends Command {
    private int index;

    public MarkCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(
            TaskList taskList, Storage storage, Ui ui) {
        try {
            if (index > taskList.length() || index < 1) {
                throw new RoofusException("Hey! It's not even in this list!");
            }
            taskList.mark(index);
            ui.mark(taskList.getTask(index - 1));
        } catch (RoofusException err){
            ui.printErrMessage(err.getMessage());
        }
    }

    @Override
    public boolean isRunning() {
        return true;
    }
}
