package roofus.command;

import roofus.RoofusException;
import roofus.Storage;
import roofus.TaskList;
import roofus.Ui;

import java.io.IOException;

/**
 * Represents a command action that adds a task in the TaskList
 * associated with the current instance of Roofus.
 */
public class DeleteCommand extends Command {
    private int index;
    public DeleteCommand(int index) {
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
        taskList.delete(index);
        try {
            storage.save(taskList);
        } catch (IOException err) {
            return ui.printErrMessage("file not saved");
        }
        return ui.delete(taskList.getTask(index - 1).toString(),
                taskList.length());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isRunning() {
        return true;
    }
}
