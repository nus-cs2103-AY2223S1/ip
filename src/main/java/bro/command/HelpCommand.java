package bro.command;

import bro.BroException;
import bro.Storage;
import bro.TaskList;
import bro.Ui;

/**
 * {@inheritDoc}
 * Helps user with the commands.
 */
public class HelpCommand extends Command {
    @Override
    public String execute(TaskList tasklist, Ui ui, Storage storage) throws BroException {
        return ui.helpUi();
    }
}
