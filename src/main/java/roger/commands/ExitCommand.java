package roger.commands;

import roger.Storage;
import roger.TaskList;
import roger.Ui;

/**
 * Encapsulates the command to exit Roger.
 */
public class ExitCommand extends Command {

    /**
     * Exit the Roger program.
     *
     * @param tasks The TaskList of the Roger program.
     * @param ui The Ui used.
     * @param storage The storage to read and load to.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showFarewell();
    }

    /**
     * Returns true, signalling the Roger program to exit.
     *
     * @return true
     */
    @Override
    public boolean isExit() {
        return true;
    }
}
