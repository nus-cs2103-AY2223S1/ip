package roofus.command;

import roofus.Storage;
import roofus.TaskList;
import roofus.Ui;

import java.io.IOException;

public class ByeCommand extends Command {
    /**
     * {@inheritDoc}
     */
    @Override 
    public void execute(TaskList taskList,
                        Storage storage, Ui ui) {
        ui.signOff();
        try {
            storage.save(taskList);
        } catch (IOException err) {
            ui.errMessage("file not saved");
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
