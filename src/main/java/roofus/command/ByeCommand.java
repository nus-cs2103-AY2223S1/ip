package roofus.command;

import java.io.IOException;

import roofus.Storage;
import roofus.TaskList;
import roofus.Ui;

/**
 * Represents a command action that saves data in the current
 * instance of TaskList and terminates the current instance of Roofus.
 */
public class ByeCommand extends Command {
    /**
     * {@inheritDoc}
     */
    @Override
    public String execute(
            TaskList taskList, Storage storage, Ui ui) {
        try {
            storage.save(taskList);
        } catch (IOException err) {
            return ui.printErrMessage("file not saved") + "\n" 
                    + ui.signOff();
        }
        return ui.signOff();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isRunning() {
        return false;
    }
}
