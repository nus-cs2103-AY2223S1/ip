package pluto.command;

import pluto.Storage;
import pluto.TaskList;
import pluto.Ui;

/**
 * Command to exit programme.
 */
public class ExitCommand extends Command {
    /**
     * {@inheritDoc}
     *
     * Prints appropriate exit message.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return ui.exitUi();
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof ExitCommand) {
            return true;
        }
        return false;
    }
}
