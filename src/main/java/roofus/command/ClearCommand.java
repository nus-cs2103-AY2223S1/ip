package roofus.command;

import roofus.Storage;
import roofus.TaskList;
import roofus.Ui;

import java.io.IOException;

/**
 * Represents a command action that clears the TaskList instance
 * associated with the current instance of Roofus.
 */
public class ClearCommand extends Command {

    /**
     * {@inheritDoc}
     */
    @Override
    public String execute(
            TaskList taskList, Storage storage, Ui ui) {
        taskList.clearStorage();
        try {
            storage.save(taskList);
        } catch (IOException err) {
            return ui.printErrMessage("file not saved");
        }
        return ui.clearStorage();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isRunning() {
        return true;
    }
}
