package pluto.command;

import pluto.Storage;
import pluto.TaskList;
import pluto.Ui;

/**
 * Command to displau all valid commands for the user.
 */
public class HelpCommand extends Command {
    /**
     * {@inheritDoc}
     *
     * Displays all valid commands.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return ui.helpUi();
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof HelpCommand) {
            return true;
        }
        return false;
    }
}
