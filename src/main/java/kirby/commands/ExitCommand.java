package kirby.commands;

import kirby.Storage;
import kirby.TaskList;
import kirby.ui.Ui;

/**
 * ExitCommand class handles the command to end the program.
 */
public class ExitCommand extends Command {
    /**
     * {@inheritDoc}
     * Quits the program.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        System.exit(0);
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isExit() {
        return true;
    }
}
