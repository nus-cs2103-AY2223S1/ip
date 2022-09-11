package pluto.command;

import pluto.Main;
import pluto.Storage;
import pluto.TaskList;
import pluto.Ui;

/**
 * Command to exit programme.
 */
public class ExitCommand extends Command {
    @Override
    public boolean isExit() {
        return true;
    }

    /**
     * {@inheritDoc}
     *
     * Prints appropriate exit message.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return ui.print("See You Later!");
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof ExitCommand) {
            return true;
        }
        return false;
    }
}
