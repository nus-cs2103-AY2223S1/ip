package gina.commands;

import gina.GinaException;
import gina.Storage;
import gina.TaskAndContactList;
import gina.Ui;

/**
 * Represents a command to exit the program.
 */
public class ExitCommand extends Command {
    /**
     * {@inheritDoc}
     */
    public String execute(TaskAndContactList taskAndContactList, Ui ui, Storage storage) throws GinaException {
        return ui.showExit();
    }

    /**
     * {@inheritDoc}
     */
    public boolean isExit() {
        return true;
    }
}
