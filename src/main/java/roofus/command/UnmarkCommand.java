package roofus.command;

import roofus.RoofusException;
import roofus.Storage;
import roofus.TaskList;
import roofus.Ui;

import java.io.IOException;

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
    public String execute(
            TaskList taskList, Storage storage, Ui ui) {
        try {
            if (index > taskList.length() || index < 1) {
                throw new RoofusException("Hey! It's not even in this list!");
            }
        } catch (RoofusException err) {
            ui.printErrMessage(err.getMessage());
        }
        taskList.unMark(index);
        try {
            storage.save(taskList);
        } catch (IOException err) {
            return ui.printErrMessage("file not saved");
        }
        return ui.unmark(taskList.getTask(index - 1));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isRunning() {
        return true;
    }
}
