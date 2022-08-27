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
    public void execute(
            TaskList taskList, Storage storage, Ui ui) {
        ui.signOff();
        try {
            storage.save(taskList);
        } catch (IOException err) {
            ui.printErrMessage("file not saved");
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isRunning() {
        return false;
    }
}
