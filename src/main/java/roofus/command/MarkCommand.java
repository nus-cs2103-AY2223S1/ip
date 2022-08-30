package roofus.command;

import roofus.RoofusException;
import roofus.Storage;
import roofus.TaskList;
import roofus.Ui;

import java.io.IOException;

/**
 * Represents a command action that marks a task in the TaskList
 * associated with the current instance of Roofus.
 */
public class MarkCommand extends Command {
    private int index;

    public MarkCommand(int index) {
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
            taskList.mark(index);
            try {
                storage.save(taskList);
            } catch (IOException err) {
                return ui.printErrMessage("file not saved");
            }
            return ui.mark(taskList.getTask(index - 1));
        } catch (RoofusException err) {
            return ui.printErrMessage(err.getMessage());
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isRunning() {
        return true;
    }
}
