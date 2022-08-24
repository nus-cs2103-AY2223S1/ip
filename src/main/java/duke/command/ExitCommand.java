package duke.command;

import duke.task.*;
import duke.ui.Ui;
import duke.data.Storage;

public class ExitCommand extends Command {

    @Override
    public boolean isExit() {
        return true;
    }

    /**
     * Displays an exit message.
     *
     * @param taskList TaskList object containing ArrayList of Task.
     * @param ui Ui object.
     * @param storage Storage object.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        ui.showExitMessage();
    }
}
