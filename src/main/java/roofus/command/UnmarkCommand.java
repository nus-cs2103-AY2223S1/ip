package roofus.command;

import roofus.RoofusException;
import roofus.Storage;
import roofus.TaskList;
import roofus.Ui;

/**
 * Represents a command action that unmarks a task in the TaskList
 * associated with the current instance of Roofus.
 */
public class UnmarkCommand extends Command {
    private int index;

    public UnmarkCommand(int index) {
        this.index = index;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void execute(TaskList taskList,
                        Storage storage, Ui ui) {
        try {
            if (index > taskList.length() || index < 1) {
                //handles index out of bounds exception
                throw new RoofusException("Hey! It's not even in this list!");
            }
        } catch (RoofusException err){
            ui.errMessage(err.getMessage());
        }
        taskList.unMark(index);
        ui.unmark(taskList.getTask(index - 1));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isRunning() {
        return true;
    }
}
